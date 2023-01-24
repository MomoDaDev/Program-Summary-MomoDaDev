const asyncFileStarter = require("./asyncFileStarter");
// starts all necessary applications

const init = async () => {
    await asyncFileStarter.start_Application('./Event_Listener/eventListener.js', function (err) {
        if (err) throw err;
    });

    
    asyncFileStarter.start_Application('./GUI_Create_Contract/initial_gui.js', function (err) {
        if (err) console.log(err);
    });
    
}

init();