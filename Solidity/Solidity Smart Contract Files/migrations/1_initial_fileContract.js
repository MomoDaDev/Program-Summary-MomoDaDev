const PathReader = require("../scripts js/PathReader");
const HashFiles = require("../scripts js/HashFiles");

const FileContract = artifacts.require("FileContract");

module.exports = function (deployer) {
  deployer.deploy(FileContract, false, getFullPath(), getHashedFile());
};

//let fileFullPath = "G:\\Diplomarbeit\\Solidity Smart Contract Files\\files\\testFile.txt";

function getFullPath() {
  return PathReader.getFullPath();
}

function getHashedFile() {
  return HashFiles.hashFile(PathReader.getFullPath());
}