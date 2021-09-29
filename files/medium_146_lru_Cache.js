/**
 * @param {number} capacity
 */
var LRUCache = function (capacity) {
    this.time = 1;
    this.capacity = capacity;
    this.keyMap = new Map();
    this.freqMap = new Map();
    //this.arrVal = Array(capacity); // 배열에 값 저장
    //this.arrFreq = Array(capacity); // 배열의 빈도수
};
/** 
 * @param {number} key
 * @return {number}
 */
LRUCache.prototype.get = function (key) {
    let val = this.keyMap.get(key);
    if (val == null) {
        return -1
    } else {
        this.freqMap.set(key, this.time++);
        return val;
    }
    
};
/** 
 * @param {number} key 
 * @param {number} value
 * @return {void}
 */
LRUCache.prototype.put = function (key, value) {
    if (!this.freqMap.has(key) && this.keyMap.size >= this.capacity) {   
        let leastFreqkey = 0;
        let leastFreqVal = 100000;
        for (let element of this.freqMap) {
            if (element[1] < leastFreqVal) // element[0] : key, element[1] : value
            {
                leastFreqkey = element[0];     
                leastFreqVal = element[1];
            }
        }
        // 가장 이전에 사용된것 삭제
        this.freqMap.delete(leastFreqkey);
        this.keyMap.delete(leastFreqkey);     
    }
     this.keyMap.set(key, value);
     this.freqMap.set(key, this.time++);
    return null
};

/** 
 * Your LRUCache object will be instantiated and called as such:
 * var obj = new LRUCache(capacity)
 * var param_1 = obj.get(key)
 * obj.put(key,value)
 */