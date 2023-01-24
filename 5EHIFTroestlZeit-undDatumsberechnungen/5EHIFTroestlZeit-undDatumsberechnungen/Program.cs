using _5EHIFTroestlZeit_undDatumsberechnungen;

const string TITLE = "Zeit- und Datumsberechnungen";
Console.Title = TITLE;
Console.ForegroundColor = ConsoleColor.Cyan;

Projekt project = new Projekt();
project.LoadParams("config.txt");
project.Load("input.txt"); // Workpackages laden
project.PrintSchedule();

Console.WriteLine("Press any key to continue . . .");
Console.ReadKey(true);