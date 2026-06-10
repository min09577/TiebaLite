1|package com.huanchengfly.tieba.post.utils.helios;
2|
3|final class XXHashEncoder extends IEncoder {
4|    public XXHashEncoder(int start, int flag) {
5|        this.length = 32;
6|        this.start = start;
7|        this.flag = flag;
8|    }
9|
10|    public EncodeResult encode(byte[] bytes, int off, int len) {
11|        XXHash xxHash = new XXHash();
12|        xxHash.update(bytes, off, len);
13|        return EncodeResult.a(new long[]{xxHash.getValue()});
14|    }
15|}
16|
17|
