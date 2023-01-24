// SPDX-License-Identifier: MIT
pragma solidity 0.8.9;

contract HelloWorldImmutable {

    string message;

    constructor(string memory _message){
        message = _message;
    }

    function hello() public view returns(string memory) {
        return message;
    }
}