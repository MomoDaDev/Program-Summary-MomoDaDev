/*
const Web3 = require('web3');
const HdWalletProvider = require('@truffle/hdwallet-provider');
const FileManager = require('../build/contracts/FileManager.json');

const privateKey ="80b4c3c9aab0c178dc141f1ea7a5de11da86bb38ecde63af3c1585d4c386218e";

const init = async () => {
    provider = new HdWalletProvider(
        privateKey,
        'http://localhost:9545/'
    );
    const web3 = new Web3(provider);
    
    const id = await web3.eth.net.getId();
    const deployedNetwork = FileManager.networks[id];
    const contract = new web3.eth.Contract(
        FileManager.abi,
        deployedNetwork.address
    );

    const addresses = await web3.eth.getAccounts();

    console.log("event listener running");

    // add eventListener

    for (let i = 0; i < 6; i++){
        await sleep(5000);

        console.log(`checking for the ${i+1}. time`);

        contract.getPastEvents('Alarm', {
        fromBlock: 0,
        }, function(error, events){ console.log(events); })
            .then(function(events){
                console.log(events); // same results as the optional callback above
        });
    }


    console.log("event listener closing . . .")

}

function sleep(ms) {
    return new Promise((resolve) => {
      setTimeout(resolve, ms);
    });
  }

init();
*/

const Web3 = require('web3');
const FileManager = require('../build/contracts/FileManager.json');

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

async function listener(contract){
    let countDown = 0;
    while(countDown == 0){
        await sleep(1000)
        // console.log("waiting...")

        contract.events.Helper()
        .on('data', event => {
          let keys = Object.keys(event.returnValues)
          countDown = event.returnValues[keys[1]];
          console.log(countDown);
        })
    }   
    return countDown;
}

async function triggerTimer(contract, addresses){
    let myBool = true;
      await contract.methods
        .timer(myBool)
        .send({ from: addresses[0] });
}

async function listenForFinishedEgg(contract){
    contract.events.Alarm()
    .on('data', event => { 
        console.log(event.returnValues)
    })
}

const init = async () =>{
    const web3 = new Web3("ws://localhost:9545");
    const id = await web3.eth.net.getId();
    const deployedNetwork = FileManager.networks[id];
    const contract = new web3.eth.Contract(
        FileManager.abi,
        deployedNetwork.address
    );
    const addresses = await web3.eth.getAccounts();

    await listener(contract)
        .then(countDown =>{
            console.log("waiting for Egg to be done in " + countDown + " seconds");
            setTimeout(() => triggerTimer(contract, addresses), countDown * 1000);
        })
        .catch(err =>{ console.log(err) })
    await listenForFinishedEgg(contract);
}
init();