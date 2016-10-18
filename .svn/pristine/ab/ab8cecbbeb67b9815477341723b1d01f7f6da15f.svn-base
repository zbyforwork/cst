package com.hxkj.cst.cheshuotong.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


import com.hxkj.cst.cheshuotong.R;
import com.hxkj.cst.cheshuotong.adapter.MyWebViewClient;
import com.hxkj.cst.cheshuotong.utils.FileUtils;

import java.io.File;

public class WebViewActivity extends Activity {
    private WebView webView;
    private String url;
    private WebViewClient webViewClient;

    public static final String TAG = "MainActivity";
    ValueCallback mUploadMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
        setContentView(R.layout.webviewlayout);
        //新页面接收数据
        Bundle bundle = this.getIntent().getExtras();
        //接收name值
        url = bundle.getString("url");
        webViewClient = new MyWebViewClient(WebViewActivity.this);
        init();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        // TODO Auto-generated method stub
        super.onNewIntent(intent);
        //MyLog.i("onNewIntent");
        Bundle bundle = intent.getExtras();
        //接收name值
        url = bundle.getString("url");
        webView.loadUrl(url);

    }

    private void init() {


        webView = (WebView) findViewById(R.id.webView);


        //启用支持javascript
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        settings .setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);


        settings.setAllowFileAccess(true);
        webView.setWebChromeClient(new MyWebChromeClient());
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(webViewClient);
        //WebView加载web资源
        webView.loadUrl(url);
    }

    //改写物理按键——返回的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();//返回上一页面
                return true;
            } else {
                this.finish();
                // System.exit(0);//退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {

        webViewClient = new MyWebViewClient(this);
        webView.setWebViewClient(webViewClient);
        super.onResume();
    }

    @Override
    protected void onPause() {
        webViewClient = null;
        webView.setWebViewClient(null);
        webView.stopLoading();

        super.onPause();
    }

    public static final int FILECHOOSER_RESULTCODE = 1;
    private static final int REQ_CAMERA = FILECHOOSER_RESULTCODE + 1;
    private static final int REQ_CHOOSE = REQ_CAMERA + 1;

    private class MyWebChromeClient extends WebChromeClient {

        @SuppressLint("NewApi")
        @Override
        public boolean onShowFileChooser(WebView webView,
                                         ValueCallback<Uri[]> filePathCallback,
                                         FileChooserParams fileChooserParams) {
            mUploadMessage = filePathCallback;

            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            intent.putExtra("crop", true);
            intent.putExtra("return-data", true);
            startActivityForResult(intent, 3);

            //  selectImage();
            //	               Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            //	               i.addCategory(Intent.CATEGORY_OPENABLE);
            //	               i.setType("*/*");
            //	                   startActivityForResult( Intent.createChooser( i, "File Chooser" ), FILECHOOSER_RESULTCODE );

            // TODO Auto-generated method stub
            return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
        }

        // For Android 3.0+
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType) {
            if (mUploadMessage != null) return;
            mUploadMessage = uploadMsg;
            selectImage();
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.addCategory(Intent.CATEGORY_OPENABLE);
            i.setType("*/*");
            startActivityForResult(Intent.createChooser(i, "File Chooser"), FILECHOOSER_RESULTCODE);
        }

        // For Android < 3.0
        public void openFileChooser(ValueCallback<Uri> uploadMsg) {
            openFileChooser(uploadMsg, "");
        }

        // For Android  > 4.1.1
        public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {

            openFileChooser(uploadMsg, acceptType);
        }


    }

    /**
     * 检查SD卡是否存在
     *
     * @return
     */
    public final boolean checkSDcard() {
        boolean flag = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        if (!flag) {
            Toast.makeText(this, "请插入手机存储卡再使用本功能", Toast.LENGTH_SHORT).show();
        }
        return flag;
    }

    String compressPath = "";

    protected final void selectImage() {
        if (!checkSDcard())
            return;
        String[] selectPicTypeStr = {"camera", "photo"};
        new AlertDialog.Builder(this)
                .setItems(selectPicTypeStr,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                switch (which) {
                                    // 相机拍摄
                                    case 0:
                                        openCarcme();
                                        break;
                                    // 手机相册
                                    case 1:
                                        chosePic();
                                        break;
                                    default:
                                        break;
                                }
                                compressPath = Environment
                                        .getExternalStorageDirectory()
                                        .getPath()
                                        + "/fuiou_wmp/temp";
                                new File(compressPath).mkdirs();
                                compressPath = compressPath + File.separator
                                        + "compress.jpg";
                            }
                        }).show();
    }

    String imagePaths;
    Uri cameraUri;

    /**
     * 打开照相机
     */
    private void openCarcme() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        imagePaths = Environment.getExternalStorageDirectory().getPath()
                + "/fuiou_wmp/temp/"
                + (System.currentTimeMillis() + ".jpg");
        // 必须确保文件夹路径存在，否则拍照后无法完成回调
        File vFile = new File(imagePaths);
        if (!vFile.exists()) {
            File vDirPath = vFile.getParentFile();
            vDirPath.mkdirs();
        } else {
            if (vFile.exists()) {
                vFile.delete();
            }
        }
        cameraUri = Uri.fromFile(vFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
        startActivityForResult(intent, REQ_CAMERA);
    }

    /**
     * 拍照结束后
     */
    private void afterOpenCamera() {
        File f = new File(imagePaths);
        addImageGallery(f);
        File newFile = FileUtils.compressFile(f.getPath(), compressPath);
    }

    /**
     * 解决拍照后在相册中找不到的问题
     */
    private void addImageGallery(File file) {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DATA, file.getAbsolutePath());
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    }

    /**
     * 本地相册选择图片
     */
    private void chosePic() {
        FileUtils.delFile(compressPath);
        Intent innerIntent = new Intent(Intent.ACTION_GET_CONTENT); // "android.intent.action.GET_CONTENT"
        String IMAGE_UNSPECIFIED = "image/*";
        innerIntent.setType(IMAGE_UNSPECIFIED); // 查看类型
        Intent wrapperIntent = Intent.createChooser(innerIntent, null);
        startActivityForResult(wrapperIntent, REQ_CHOOSE);
    }

    /**
     * 选择照片后结束
     *
     * @param data
     */
    private Uri afterChosePic(Intent data) {

        // 获取图片的路径：
        String[] proj = {MediaStore.Images.Media.DATA};
        // 好像是android多媒体数据库的封装接口，具体的看Android文档
        Cursor cursor = managedQuery(data.getData(), proj, null, null, null);
        if (cursor == null) {
            Toast.makeText(this, "上传的图片仅支持png或jpg格式", Toast.LENGTH_SHORT).show();
            return null;
        }
        // 按我个人理解 这个是获得用户选择的图片的索引值
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        // 将光标移至开头 ，这个很重要，不小心很容易引起越界
        cursor.moveToFirst();
        // 最后根据索引值获取图片路径
        String path = cursor.getString(column_index);
        if (path != null && (path.endsWith(".png") || path.endsWith(".PNG") || path.endsWith(".jpg") || path.endsWith(".JPG"))) {
            File newFile = FileUtils.compressFile(path, compressPath);
            return Uri.fromFile(newFile);
        } else {
            Toast.makeText(this, "上传的图片仅支持png或jpg格式", Toast.LENGTH_SHORT).show();
        }
        return null;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("requestCode" + requestCode + ",resultCode" + resultCode);
        if (resultCode == RESULT_OK) {
            if (requestCode == 3) {
                //					Uri uri = data.getData();
                Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "1.png"));

                System.out.println(uri.getPath());
                mUploadMessage.onReceiveValue(new Uri[]{uri});
                //Log.i("yaochuan", uri.getPath());

            }
        }

    }


    //		@Override
    //		protected void onActivityResult(int requestCode, int resultCode,
    //				Intent intent) {
    ////			if (requestCode == FILECHOOSER_RESULTCODE) {
    ////				if (null == mUploadMessage)
    ////					return;
    ////				Uri result = intent == null || resultCode != RESULT_OK ? null
    ////						: intent.getData();
    ////				mUploadMessage.onReceiveValue(result);
    ////				mUploadMessage = null;
    ////			}
    //
    //			if (null == mUploadMessage)
    //				return;
    //			Uri uri = null;
    //			if(requestCode == REQ_CAMERA ){
    //				afterOpenCamera();
    //				uri = cameraUri;
    //			}else if(requestCode == REQ_CHOOSE){
    //				uri = afterChosePic(intent);
    //			}
    //			mUploadMessage.onReceiveValue(new Uri[]{uri});
    //		//	mUploadMessage = null;
    //			super.onActivityResult(requestCode, resultCode, intent);
    //		}


}
