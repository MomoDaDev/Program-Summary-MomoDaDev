using _5EHIFTroestlSprungturm.Common;
using static _5EHIFTroestlSprungturm.ENUM_AND_CONSTANTS;

namespace _5EHIFTroestlSprungturm.Facade
{
    internal class Manager
    {
        #region Properties
        private DivingTowerSimulator simulator { get; set; }
        private ConsoleOutput consoleOutput { get; set; }
        #endregion

        #region Initialization
        public Manager()
        {
            simulator = new DivingTowerSimulator();
            consoleOutput = new ConsoleOutput(0, 0, 0, 1, 0, 2, false, 0, JumpState.Idle);
        }
        #endregion
        
        #region Manage Simulation
        public void StartDivingTowerSimulation()
        {
            // Subscribe to output of simulation
            SubscribeToSimulationOutput();

            // Subscribe to event SimulationEnded from DivingTowerSimulator
            SubscribeToSimulationEnded();

            // Start Simulation
            simulator.StartSimulation();
            consoleOutput.RunningState = true;
        }
        #endregion

        #region Event Methods
        public void OnSimulationEnded(object? sender, EventArgs e)
        {
            // update console output
            consoleOutput.RunningState = false;
            consoleOutput.UpdateOutput();

            // Unsubscribe from output of simulation
            UnsubscribeFromSimulationOutput();

            // Unsubscribe from SimulationEnded
            simulator.SimulationEnded -= this.OnSimulationEnded;
        }
        #endregion

        #region SubscribtionManaging
        private void SubscribeToSimulationOutput()
        {
            simulator.KidIsJumping += consoleOutput.OnKidJumps;
            simulator.QueueCountUpdated += consoleOutput.OnQueueCountUpdated;
        }

        private void SubscribeToSimulationEnded()
        {
            simulator.SimulationEnded += this.OnSimulationEnded;
        }

        private void UnsubscribeFromSimulationOutput()
        {
            simulator.KidIsJumping -= consoleOutput.OnKidJumps;
            simulator.QueueCountUpdated -= consoleOutput.OnQueueCountUpdated;
        }
        #endregion
    }
}
