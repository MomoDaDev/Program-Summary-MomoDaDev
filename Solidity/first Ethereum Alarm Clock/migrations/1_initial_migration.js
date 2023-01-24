const DelayedPayment = artifacts.require("DelayedPayment");

module.exports = function (deployer) {
  deployer.deploy(DelayedPayment);
};
