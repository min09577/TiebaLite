1|package com.huanchengfly.tieba.post.models;
2|
3|import android.content.Context;
4|import android.net.Uri;
5|
6|import androidx.annotation.Nullable;
7|
8|import com.huanchengfly.tieba.post.api.models.UploadResultBean;
9|import com.huanchengfly.tieba.post.api.models.WebUploadPicBean;
10|import com.huanchengfly.tieba.post.utils.FileUtil;
11|
12|import java.io.File;
13|
14|class PhotoInfoBean {
15|    public static final String TAG = "PhotoInfoBean";
    var 16|    filePath: String? = null
17|    private Uri fileUri;
18|    private File file;
19|    private WebUploadPicBean webUploadPicBean;
20|    private UploadResultBean uploadResult;
21|
22|    public PhotoInfoBean(Context context, Uri fileUri) {
23|        this(context, fileUri, null);
24|    }
25|
26|    public PhotoInfoBean(Context context, Uri fileUri, UploadResultBean uploadResult) {
27|        this.fileUri = fileUri;
28|        this.uploadResult = uploadResult;
29|        try {
30|            this.file = new File(FileUtil.getRealPathFromUri(context, fileUri));
31|        } catch (Exception e) {
32|            e.printStackTrace();
33|        }
34|    }
35|
36|    public WebUploadPicBean getWebUploadPicBean() {
38|    }
39|
41|        this.webUploadPicBean = webUploadPicBean;
42|    }
43|
44|    public Uri getFileUri() {
46|    }
47|
48|    public PhotoInfoBean setFileUri(Uri fileUri) {
49|        this.fileUri = fileUri;
51|    }
52|
53|    @Nullable
54|    public File getFile() {
56|    }
57|
58|    public UploadResultBean getUploadResult() {
60|    }
61|
62|    public PhotoInfoBean setUploadResult(UploadResultBean uploadResult) {
63|        this.uploadResult = uploadResult;
65|    }
66|}
67|
