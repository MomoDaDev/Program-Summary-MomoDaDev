const Web3 = require('web3');
const FileManagerJSON = require('../build/contracts/FileManager.json');
const HdWalletProvider = require('@truffle/hdwallet-provider');
const fs = require('fs');

const address = "0x03250794d430ad4e7bd46ece99b7146f7fead1b9";
const privateKey ="80b4c3c9aab0c178dc141f1ea7a5de11da86bb38ecde63af3c1585d4c386218e";

let provider;
let web3;

let id;
let deployedNetwork;
let contract

const init = async () => {
    provider = new HdWalletProvider(
        privateKey,
        'http://localhost:9545/'
    );
    web3 = new Web3(provider);

    id = await web3.eth.net.getId();
    deployedNetwork = FileManagerJSON.networks[id];
    contract = new web3.eth.Contract(
        FileManagerJSON.abi,
        deployedNetwork.address
      );
}

async function deployContract(name, fullpath, alarm, fileHashValue) {
    console.log("in deployContract function");
    
    contract.deploy({
        data: FileManagerJSON.bytecode,
        // You can omit the asciiToHex calls, as the contstructor takes strings. 
        // Web3 will do the conversion for you.
        arguments: [true, name, fullpath, alarm, fileHashValue] 
    }).send({
        from: address
    }).then((instance) => {
        console.log("Contract mined at " + instance.options.address);
        //fs.writeFileSync(`_${instance.options.address}.txt`,`${instance.options.address}`);
    });

 
    //Migration_FileManager.plsDeploy(name, fullpath, alarm, fileHashValue);

    console.log("successfully deployed");

    contract.methods.alarmRings().send({from: address});
}

init();

module.exports = {
    deployContract
}