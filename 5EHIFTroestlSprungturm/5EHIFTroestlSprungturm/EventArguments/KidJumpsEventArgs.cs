using static _5EHIFTroestlSprungturm.ENUM_AND_CONSTANTS;

namespace _5EHIFTroestlSprungturm.EventArguments
{
    internal class KidJumpingEventArgs : EventArgs
    {
        public JumpState jumpState { get; set; }

        public KidJumpingEventArgs(JumpState jumpState)
        {
            this.jumpState = jumpState;
        }
    }
}
