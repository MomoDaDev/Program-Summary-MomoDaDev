using System;

namespace _5EHIFTroestlRegenwasserfalle
{
    class Program
    {
        
        // Driver code
        public static void Main(String[] args)
        {
            const string TITLE = "Regenwasserfaelle";
            Console.Title = TITLE;
            Console.ForegroundColor = ConsoleColor.Cyan;

            int[] arr1 = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
            int[] arr2 = { 4, 2, 0, 3, 2, 5 };

            int result1 = Trap(arr1);
            int result2 = Trap(arr2);

            Console.WriteLine($"Result: {result1} {result2}");
            
            if (result1 == 6 && result2 == 9)
                Console.WriteLine("Nice! :)");
            else
                Console.WriteLine(":(((");

            Console.WriteLine("Press any key to continue . . .");
            Console.ReadKey(true);
        }

        public static int Trap(int[] height)
        {
            // use n for array length
            int n = height.Length;

            // To store the maximum water
            // that can be stored
            int res = 0;

            // For every element of the array
            // except first and last element
            for (int i = 1; i < n - 1; i++)
            {

                // Find maximum element on its left
                int left = height[i];
                for (int j = 0; j < i; j++)
                {
                    left = Math.Max(left, height[j]);
                }

                // Find maximum element on its right
                int right = height[i];
                for (int j = i + 1; j < n; j++)
                {
                    right = Math.Max(right, height[j]);
                }

                // Update maximum water value
                res += Math.Min(left, right) - height[i];
            }
            return res;
        }
    }
}
