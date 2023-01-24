using _5EHIFTroestl_Recycling;

const string TITLE = "TITLE";
Console.Title = TITLE;
Console.ForegroundColor = ConsoleColor.Cyan;

Manager manager = new Manager();
manager.PlaySimulation();

Console.WriteLine("press any key to continue . . .");
Console.ReadKey(true);