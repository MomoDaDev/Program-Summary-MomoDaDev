const Web3 = require('web3');
const MyContract = require('./build/contracts/MyContract.json')

const init = async () => {
    // establish connection to local network
    const web3 = new Web3('http://localhost:9545');

    // get our own id
    const id = await web3.eth.net.getId();
    const deployedNetwork = MyContract.networks[id];

    // create our contract
    // Note: you need to compile your Contract or it's not going to create MyContract.json
    //       which is necessary for the arguments to be valid
    const contract = new web3.eth.Contract(
        MyContract.abi,
        deployedNetwork.address
    );

    // getting all addresses
    const addresses = await web3.eth.getAccounts();

    // setting the data to 10
    const receipt = await contract.methods.setData(10).send({
        from: addresses[0]
    });
    // print receipt
    console.log(receipt);

    // get data and print the value
    const data = await contract.methods.getData().call();
    // should be 1ß
    console.log(data);

    // functionCall should be sendEther
    await contract.methods.sendEther().send({
        from: addresses[0],
        value: web3.utils.toBN('10000')
    });
    // check result
    console.log(await contract.methods.functionCalled().call());

    // functionCall should be fallBack
    await web3.eth.sendTransaction({
        from: addresses[0],
        to: contract.options.address,
        value: '100000'
    });

    // check result
    console.log(await contract.methods.functionCalled().call());

    // send money from one address to another
    await web3.eth.sendTransaction({
        from: addresses[0],
        to: addresses[1],
        value: '100000'
    });
}

init();