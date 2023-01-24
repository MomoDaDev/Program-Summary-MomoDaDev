using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using static _5EHIFTroestlCasino8.ConstantsAndEnums;

namespace _5EHIFTroestlCasino8
{
    class Game
    {
        #region Properties
        private Random rnd = new Random();
        public string ParticipantName { get; set; }
        public bool HasMoney { get; set; }
        private double balance;
        public double Balance
        {
            get { return balance; }
            set
            {
                if (value <= 0)
                    HasMoney = false;
                balance = value;
            }
        }
        public string[] Rounds { get; private set; }
        #endregion

        #region Initialize
        public Game(string participantName, bool hasMoney, double balance, string[] rounds)
        {
            ParticipantName = participantName;
            HasMoney = hasMoney;
            Balance = balance;
            Rounds = rounds;
        }
        #endregion

        #region Play Game
        public bool PlayRound(int numRound, bool print)
        {
            // If Round doesn't exists then the Round is skipped
            if (numRound >= Rounds.Length || numRound < 0)
                return HasMoney;

            string round = Rounds[numRound];
            double stake = double.Parse(round.Split(' ')[1]);
            StakeType stakeType;
            int pickedNumber = rnd.Next(0, MAXNUMBER + 1);
            List<int> myNumbers = new List<int>();
            bool won = false;
            double oldBalance = Balance;

            // initialize Numbers
            if (round.Substring(0, 1) == "G")
            {
                stakeType = StakeType.G;
                for (int i = 2; i <= MAXNUMBER; i += 2)
                {
                    myNumbers.Add(i);
                }
            } else if (round.Substring(0, 1) == "U")
            {
                stakeType = StakeType.U;
                for (int i = 1; i <= 8; i += 2)
                {
                    myNumbers.Add(i);
                }
            } else
            {
                stakeType = StakeType.Number;
                myNumbers.Add(int.Parse(round.Substring(0, 1)));
            }

            // check if won
            if (myNumbers.Contains(pickedNumber))
            {
                Balance += CalculatePrice(stakeType, stake);
                won = true;
            } else
            {
                Balance -= stake;
            }
            if (print)
                Console.WriteLine($"Round Played ({numRound}) by {ParticipantName}: [\n" +
                    $"   Old Balance: {oldBalance}\n" +
                    $"   Stake: {stake}\n" +
                    $"   Bet: {round}\n" +
                    $"   won?: {won}\n" +
                    $"   New Balance: {Balance}\n" +
                    $"]");


            return HasMoney;
        }
        
        private double CalculatePrice(StakeType stakeType, double stake)
        {
            if (stakeType == StakeType.G || stakeType == StakeType.U)
                return stake * 2;
            return stake * 7;
        }


        #endregion

        #region ToString
        public override string ToString()
        {
            return JsonConvert.SerializeObject(this);
        }
        #endregion
    }
}
