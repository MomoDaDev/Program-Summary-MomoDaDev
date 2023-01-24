const Web3 = require('web3');
const MyContract = require('./build/contracts/MyContract.json');

const init = async () => {
    const web3 = new Web3('http://localhost:9545/');
    
    const id = await web3.eth.net.getId();
    const deployedNetwork = MyContract.networks[id];
    const contract = new web3.eth.Contract(
        MyContract.abi,
        deployedNetwork.address
    );

    const addresses = await web3.eth.getAccounts();

    // add eventListener
    contract.events.MyEvent({
        fromBlock: 0
    })
    .on('data', event => console.log(result.events));

    // only reads events when you send a transaction (not useful in our case)
    const receipt = await contract.methods
    .emitEvent('hey')
    .send({
        from: addresses[0]
    });
    console.log(receipt.events);

    // send another transaction
    await contract.methods
    .emitEvent('hey2')
    .send({
        from: addresses[0]
    });
    //console.log(receipt.events);

    // receive the past events
    const result = await contract.getPastEvents(
        'MyEvent',
        {
            filter: {
                value: 'hey'
            },
            fromBlock: 0
        }
    );
    //console.log(result);

    await contract.methods
    .emitEvent('hey3')
    .send({
        from: addresses[0]
    });

    await contract.methods
    .emitEvent('hey4')
    .send({
        from: addresses[0]
    });

    await new Promise(resolve => setTimeout(() => resolve, 8000));
}

init();