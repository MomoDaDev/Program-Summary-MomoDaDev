// SPDX-License-Identifier: MIT
pragma solidity 0.8.9;

contract FileManager {
  // properties
  address public owner = msg.sender;
  bool private isRestrictedToOwner;

  string public name;
  string public fileLocation;
  uint public alarm;
  string public fileHash;  // the field fileHash can stay public since the only way to change the value is through a function which there isn't
  
  uint public createdTime;
  bool public finished;

  // event properties
  event Alarm(address indexed _owner, string _name, string _fileLocation, uint _alarm, string _fileHash);
  event Helper(uint indexed alarm); // Helper Event

  // constructors
  constructor(bool _isRestrictedToOwner, string memory _name, string memory _fileLocation, uint _alarm, string memory _fileHash)
  timerGreaterZero(_alarm) {
    isRestrictedToOwner = _isRestrictedToOwner;
    name = _name;
    fileLocation = _fileLocation;
    alarm = _alarm;
    fileHash = _fileHash;
    createdTime = block.timestamp;
    emitHelper();
    alarmRings();
  }

  // modifiers
  modifier restricted() {
    require(-
      msg.sender == owner || !isRestrictedToOwner,
      "This function is restricted to the contract's owner"
    );
    _;
  }

  modifier timerGreaterZero(uint _alarm){
    require(_alarm > 0);
    _;
  }

  // event emits
  function alarmRings() public {
    emit Alarm(msg.sender, name, fileLocation, alarm, fileHash);
  }

  function emitHelper() public{ // external geht hier nicht weil das kann der constructor nicht callen, daher public
    emit Helper(alarm);
  }

  function timer(bool _finished) external{
      require(block.timestamp > createdTime + (alarm - 1), "Time has not yet passed");
      finished = _finished; 
      alarmRings();
  }
}
