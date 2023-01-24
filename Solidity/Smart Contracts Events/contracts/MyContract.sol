// SPDX-License-Identifier: MIT
pragma solidity ^0.5.0;

contract MyContract {
    // dates are identified as integers
    // limit of 3 indexed fields
    event MyEvent (
        uint indexed id,
        uint indexed date,
        string indexed value
    );
    uint nextId;

    function emitEvent(string calldata value) external {
        emit MyEvent(nextId, now, value);
        nextId++;
    }
}