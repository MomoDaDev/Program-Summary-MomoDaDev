namespace _5EHIFTroestlSprungturm
{
    internal class ENUM_AND_CONSTANTS
    {
        #region Enums
        public enum JumpState
        {
            Idle,
            Acending,
            SteppingForward,
            Jumping,
            LeavingSwimmingpool
        }
        #endregion

        #region Constants
        public static int SPEED = 50;

        public static int DIVING_TOWER_TIME_OPEN = 1_800_000 / SPEED;
        public static int JUMP_CYCLE_TIME = 20_000 / SPEED;

        public static int KIDS_JOINING_TIME = 1_200_000 / SPEED;
        public static int KIDS_LEAVING_TIME = 600_000 / SPEED;
        public static int KID_ENQUEUE_MIN_TIME = 10_000 / SPEED;
        public static int KID_ENQUEUE_MAX_TIME = 60_000 / SPEED;

        public static int KIDS_NEARBY = 10;
        public static int KIDS_JOINING_PER_MINUTE = 1;
        public static int KIDS_LEAVING_PER_MINUTE = 2;
        public static int MINUTE = 60_000 / SPEED;
        public static int SECOND = 1000 / SPEED;

        public static int SIMULATIONSTEP_TIME = 50;
        #endregion
    }
}
