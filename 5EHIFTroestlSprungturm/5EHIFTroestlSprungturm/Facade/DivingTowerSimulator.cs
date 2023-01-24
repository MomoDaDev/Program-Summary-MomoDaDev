using _5EHIFTroestlSprungturm.EventArguments;
using System.Timers;
using static _5EHIFTroestlSprungturm.ENUM_AND_CONSTANTS;

namespace _5EHIFTroestlSprungturm.Facade
{
    internal struct DivingTowerEvent
    {
        public DateTime timespamp;
        public string _event;
        public DivingTowerEvent(DateTime timespamp, string _event) : this()
        {
            this.timespamp = timespamp;
            this._event = _event;
        }
    }
    internal class DivingTowerSimulator
    {
        #region Properties
        Random random = new Random();
        private System.Timers.Timer _timer = new System.Timers.Timer(SIMULATIONSTEP_TIME);

        private DateTime simulationStart;
        private DateTime simulationEnd;

        private bool Kidsjoining() => DateTime.Now < simulationStart.AddMilliseconds(KIDS_JOINING_TIME);

        private int queueCount;
        private int QueueCount
        { 
            get { return queueCount; }
            set 
            {
                UpdatedQueueCount(value);
                queueCount = value; 
            }
        }
        private JumpState _jumpState;
        private JumpState _JumpState
        {
            get { return _jumpState; }
            set 
            {
                KidJumping(value);
                _jumpState = value; 
            }
        }

        List<DivingTowerEvent> divingTowerEvents;
        #endregion

        #region Simulation
        public void StartSimulation()
        {
            simulationStart = DateTime.Now;
            simulationEnd = simulationStart.AddMilliseconds(DIVING_TOWER_TIME_OPEN);
            InitializationInterval();
            Thread.Sleep(200);

            _timer.Elapsed += OnInterval;
            _timer.Start();
        }

        private void OnInterval(object? source, ElapsedEventArgs e)
        {
            if (DateTime.Now >= simulationEnd)
                EndSimulation();

            List<DivingTowerEvent> listToRemove = new List<DivingTowerEvent>();
            List<DivingTowerEvent> listToAdd = new List<DivingTowerEvent>();

            lock (divingTowerEvents)
            {
                foreach (var dte in divingTowerEvents)
                {
                    if (DateTime.Now >= dte.timespamp)
                    {
                        switch (dte._event)
                        {
                            case "Acending":
                                listToRemove.Add(dte);
                                _JumpState = JumpState.Acending;
                                listToAdd.Add(new DivingTowerEvent(DateTime.Now.AddMilliseconds(SECOND * 5), "SteppingForward"));
                                break;
                            case "SteppingForward":
                                listToRemove.Add(dte);
                                _JumpState = JumpState.SteppingForward;
                                listToAdd.Add(new DivingTowerEvent(DateTime.Now.AddMilliseconds(SECOND * 5), "Jumping"));
                                break;
                            case "Jumping":
                                listToRemove.Add(dte);
                                _JumpState = JumpState.Jumping;
                                listToAdd.Add(new DivingTowerEvent(DateTime.Now.AddMilliseconds(SECOND * 5), "LeavingSwimmingpool"));
                                break;
                            case "LeavingSwimmingpool":
                                listToRemove.Add(dte);
                                _JumpState = JumpState.LeavingSwimmingpool;
                                QueueCount--;
                                listToAdd.Add(new DivingTowerEvent(DateTime.Now.AddSeconds(random.Next(1, 7)), "KidEnqueues"));
                                listToAdd.Add(new DivingTowerEvent(DateTime.Now.AddMilliseconds(SECOND * 5), "Acending"));
                                break;
                            case "KidEnqueues":
                                listToRemove.Add(dte);
                                QueueCount++;
                                break;
                            case "KidJoins":
                                listToRemove.Add(dte);
                                QueueCount += KIDS_JOINING_PER_MINUTE;
                                if (Kidsjoining())
                                    listToAdd.Add(new DivingTowerEvent(DateTime.Now.AddMilliseconds(MINUTE), "KidJoins"));
                                else
                                    listToAdd.Add(new DivingTowerEvent(DateTime.Now.AddMilliseconds(MINUTE), "KidLeaves"));
                                break;
                            case "KidLeaves":
                                listToRemove.Add(dte);
                                QueueCount -= KIDS_LEAVING_PER_MINUTE;
                                if (Kidsjoining())
                                    listToAdd.Add(new DivingTowerEvent(DateTime.Now.AddMilliseconds(MINUTE), "KidJoins"));
                                else
                                    listToAdd.Add(new DivingTowerEvent(DateTime.Now.AddMilliseconds(MINUTE), "KidLeaves"));
                                break;
                            default:
                                break;
                        }
                    }
                }
            }

            // remove passed events
            foreach (var dte in listToRemove)
            {
                lock (divingTowerEvents)
                {
                    divingTowerEvents.Remove(dte);
                }
            }

            // add new events
            foreach (var dte in listToAdd)
            {
                lock (divingTowerEvents)
                {
                    divingTowerEvents.Add(dte);
                }
            }
        }

        private void InitializationInterval()
        {
            divingTowerEvents = new List<DivingTowerEvent>();
            QueueCount = KIDS_NEARBY;
            _JumpState = JumpState.Idle;
            lock (divingTowerEvents)
            {
                divingTowerEvents.Add(new DivingTowerEvent(DateTime.Now, "Acending"));
            }
            lock (divingTowerEvents)
            {
                divingTowerEvents.Add(new DivingTowerEvent(DateTime.Now, "KidJoins"));
            }
        }

        public void EndSimulation()
        {
            _timer.Elapsed -= OnInterval;
            _timer.Stop();
            SimulationEnding();
        }
        #endregion

        #region Events
        public event EventHandler<KidJumpingEventArgs>? KidIsJumping;
        private void KidJumping(JumpState jumpState)
        {
            KidJumpingEventArgs e = new KidJumpingEventArgs(jumpState);
            OnKidJumping(e);
        }

        protected virtual void OnKidJumping(KidJumpingEventArgs e)
        {
            KidIsJumping?.Invoke(this, e);
        }

        public event EventHandler<QueueCountUpdatedEventArgs>? QueueCountUpdated;
        private void UpdatedQueueCount(int queueCount)
        {
            QueueCountUpdatedEventArgs e = new QueueCountUpdatedEventArgs(queueCount);
            OnQueueCountUpdated(e);
        }

        protected virtual void OnQueueCountUpdated(QueueCountUpdatedEventArgs e)
        {
            QueueCountUpdated?.Invoke(this, e);
        }

        public event EventHandler<EventArgs>? SimulationEnded;
        private void SimulationEnding()
        {
            EventArgs e = new EventArgs();
            OnSimulationEnded(e);
        }

        protected virtual void OnSimulationEnded(EventArgs e)
        {
            SimulationEnded?.Invoke(this, e);
        }
        #endregion
    }
}
