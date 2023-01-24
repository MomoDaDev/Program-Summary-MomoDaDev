const FileManager = artifacts.require("FileManager");
const asyncFileStarter = require("../asyncFileStarter");

module.exports = function (deployer) {
  for (let i = 0; i < data.length; i++) {
    deployer.deploy(FileManager, false, data[i][0], data[i][1], data[i][2], data[i][3]);
  }
};
/*
let cock = async (name, fullpath, alarm, fileHashValue) => {
  deployer.deploy(FileManager, true, name, fullpath, alarm, fileHashValue);
}

module.exports = {
  cock
};
*/

let data = [
  [ 'this gives', 'me', 5, 'womAn' ],
  [ 'this gives', 'me', 10, 'Binschiling' ],
  [ 'this gives', 'me', 15, 'Object' ],
  [ 'this gives', 'me', 0, 'will to live' ],
  [ 'this gives', 'me', 25, 'DBI pluserl' ]
]

const init = async () => {
  await asyncFileStarter.start_Application('../index.js', function (err) {
      if (err) throw err;
  });

  /*
  start_Application('./GUI_Create_Contract/initial_gui.js', function (err) {
      if (err) console.log(err);
  });
  */    
}

init();