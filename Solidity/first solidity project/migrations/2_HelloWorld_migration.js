const HelloWorld = artifacts.require("HelloWorld"); // "HelloWorld" comes from name of contract
const HelloWorldImmutable = artifacts.require("HelloWorldImmutable");

module.exports = function (deployer) {
  deployer.deploy(HelloWorld, test());
  deployer.deploy(HelloWorldImmutable, "This is immutable now");
};

function test() {
  return "Hello World constructor";
}