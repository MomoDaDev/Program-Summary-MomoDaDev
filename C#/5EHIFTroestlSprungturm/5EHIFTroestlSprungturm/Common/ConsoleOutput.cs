using _5EHIFTroestlSprungturm.EventArguments;
using static _5EHIFTroestlSprungturm.ENUM_AND_CONSTANTS;

namespace _5EHIFTroestlSprungturm.Common
{
    internal class ConsoleOutput
    {
        #region Properties
        public int JumpStatePosX { get; set; }
        public int JumpStatePosY { get; set; }
        public int QueueCountPosX { get; set; }
        public int QueueCountPosY { get; set; }
        public int RunningStatePosX { get; set; }
        public int RunningStatePosY { get; set; }

        public bool RunningState { get; set; }
        public int QueueCount { get; set; }
        public JumpState CurrentJumpState { get; set; }
        #endregion

        #region Initialization
        public ConsoleOutput(int jumpStatePosX, int jumpStatePosY, int queueCountPosX, int queueCountPosY, 
            int runningStatePosX, int runningStatePosY, bool runningState, int queueCount, JumpState currentJumpState)
        {
            JumpStatePosX = jumpStatePosX;
            JumpStatePosY = jumpStatePosY;
            QueueCountPosX = queueCountPosX;
            QueueCountPosY = queueCountPosY;
            RunningStatePosX = runningStatePosX;
            RunningStatePosY = runningStatePosY;
            RunningState = runningState;
            QueueCount = queueCount;
            CurrentJumpState = currentJumpState;
        }
        #endregion

        #region Print Methods
        public void UpdateOutput()
        {
            Console.SetCursorPosition(RunningStatePosX, RunningStatePosY);
            Console.Write($"Simulation is running?: {RunningState}               ");

            Console.SetCursorPosition(QueueCountPosX, QueueCountPosY);
            Console.Write($"Kids in the queue: {QueueCount}                      ");

            Console.SetCursorPosition(JumpStatePosX, JumpStatePosY);
            Console.Write($"Current Jump State: {CurrentJumpState}               ");
        }
        #endregion

        #region Event Methods
        public void OnKidJumps(object? sender, KidJumpingEventArgs e)
        {
            CurrentJumpState = e.jumpState;
            UpdateOutput();
        }

        public void OnQueueCountUpdated(object? sender, QueueCountUpdatedEventArgs e)
        {
            QueueCount = e.QueueCount;
            UpdateOutput();
        }
        #endregion
    }
}
