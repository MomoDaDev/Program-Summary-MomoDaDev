// SPDX-License-Identifier: MIT
pragma solidity 0.5.16;

contract SchedulerInterface  {
    
    function schedule(address _toAddress,
                  bytes memory _callData,
                  uint[8] memory _uintArgs)
    public payable returns (address);
}