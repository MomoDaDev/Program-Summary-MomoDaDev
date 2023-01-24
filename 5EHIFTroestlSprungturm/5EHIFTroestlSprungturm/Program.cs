using _5EHIFTroestlSprungturm.Facade;

const string TITLE = "Sprungturm Simulation";
Console.Title = TITLE;
Console.ForegroundColor = ConsoleColor.Cyan;

Manager manager = new Manager();
manager.StartDivingTowerSimulation();

Console.ReadKey(true);