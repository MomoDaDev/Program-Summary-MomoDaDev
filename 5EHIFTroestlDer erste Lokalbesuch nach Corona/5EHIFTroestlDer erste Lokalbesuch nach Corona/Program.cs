using _5EHIFTroestlDer_erste_Lokalbesuch_nach_Corona;

const string TITLE = "Der erste Lokalbesuch nach Corona";
Console.Title = TITLE;
Console.ForegroundColor = ConsoleColor.Cyan;

Simulation simulation = new Simulation(InitializePeople.Load(), 0, new TimeOnly(10, 0), 
    new TimeOnly(19, 0), InitializeDrinks.Load());
simulation.StartSimulation();

Console.ReadKey(true);