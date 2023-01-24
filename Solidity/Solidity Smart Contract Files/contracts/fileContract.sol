// SPDX-License-Identifier: MIT
pragma solidity 0.8.9;

contract FileContract {
  // properties
  address public owner = msg.sender;
  bool private isRestrictedToOwner;

  // the field fileHash can stay public since the only way to change the value is through a function which there isn't
  string public fileLocation;
  string public fileHash;

  // constructor
  constructor(bool _isRestrictedToOwner, string memory _fileLocation, string memory _fileHash) {
    isRestrictedToOwner = _isRestrictedToOwner;
    fileLocation = _fileLocation;
    fileHash = _fileHash;
  }

  // modifiers
  modifier restricted() {
    require(
      msg.sender == owner || !isRestrictedToOwner,
      "This function is restricted to the contract's owner"
    );
    _;
  }

  // functions
  function changeFileLocation(string memory _fileLocation) public {
    fileLocation = _fileLocation;
  }

  // note: tried to implement a toString() method for test purposes but putting strings togehter is pretty difficult
  //       already tried:  string(abi.encodePacked(str1, str2));
}
