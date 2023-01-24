namespace _5EHIFTroestlSprungturm.EventArguments
{
    internal class QueueCountUpdatedEventArgs : EventArgs
    {
        public int QueueCount { get; set; }

        public QueueCountUpdatedEventArgs(int queueCount)
        {
            QueueCount = queueCount;
        }
    }
}
