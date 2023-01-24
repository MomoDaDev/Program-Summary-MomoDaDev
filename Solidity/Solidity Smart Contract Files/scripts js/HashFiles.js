const crypto = require('crypto');
const fs = require('fs');

const hashSum = crypto.createHash('sha256');

function checkFilePath(filePath){
    try{
        let fileBuffer = fs.readFileSync(filePath,'utf8');
        hashSum.update(fileBuffer);
        let hex = hashSum.digest('hex');
        return hex;
    } catch {
        return "";
    }
}

module.exports = {
    hashFile: checkFilePath
}