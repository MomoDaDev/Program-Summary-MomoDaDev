const fs = require('fs');

function readFullPath(){
    return fs.readFileSync('data/FullPath.txt',
    {encoding:'utf8', flag:'r'});
}

module.exports = {
    getFullPath: readFullPath
}