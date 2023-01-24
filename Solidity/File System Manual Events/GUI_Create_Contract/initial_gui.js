const readline = require("readline");
const HashFiles = require("../File_Hashing/hashFiles");
const CD = require("../Web3_Application/contractDeployment");
//let Woman_with = require("../migrations/2_initial_fileManager");

const appName = "(CC GUI) " // Create Contract Graphical User Interface

let name, fullpath, alarm, fileHashValue;

function ReadInput() {
    let rl = readline.createInterface({
        input: process.stdin,
        output: process.stdout
    });

    rl.on("close", function() {
        // continue here after the user input
    
        if (name + fullpath + alarm !== ""){

            // THIS CODE IS FOR TEST PURPOSES!!!
            if (name === "test1"){
                fullpath = "D:\\SteamLibrary\\libraryfolder.vdf";
                alarm = Date.now() + 30000; // around 30 seconds
                
                console.log(alarm);
            }
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

            console.log("\nhashing file . . .");
            // get the hash value
            fileHashValue = HashFiles.hashFile(fullpath);

            console.log(`HashValue: ${fileHashValue}`);

            // convert dateTime to number
            let unixTimestamp = Math.floor(new Date(alarm).getTime()/1000);
            console.log("unixTimeStamp: " + unixTimestamp);

            // deploy contract
            CD.deployContract(name, fullpath, alarm, fileHashValue);
            //Woman_with.deploy(name, fullpath, alarm, fileHashValue);

            // repeat asking the user for input
            ReadInput();
        } else {
            process.exit(0);
        }
    });

    rl.question("Name of Entry: ", function (_name) {
        rl.question("Full Path of File: ", function (_fullpath) {
            rl.question("alarm (DD/MM/YYYY hh:mm:ss): ", function (_alarm) {
                name = _name;
                fullpath = _fullpath;
                alarm = _alarm;
                rl.close();
            });
        });
    });
}

ReadInput();

