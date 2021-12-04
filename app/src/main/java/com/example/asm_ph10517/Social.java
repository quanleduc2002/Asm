package com.example.asm_ph10517;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Logger;

public class Social extends AppCompatActivity {
    LoginButton loginButton;
    CallbackManager callbackManager;
    TextView tvName,tvEmail,tvFirstName;
    Button button;
    ProfilePictureView profilePictureView;
    String email,name,firstname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setTitle("Social");
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    "com.example.asm_ph10517",                  //Insert your own package name.
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//
//
//        } catch (NoSuchAlgorithmException | PackageManager.NameNotFoundException e) {
//
//        }
        overridePendingTransition(R.anim.toleft,R.anim.toright);
        setContentView(R.layout.activity_social);
        loginButton = findViewById(R.id.Loginfb);
        tvName=findViewById(R.id.Name);
        tvEmail=findViewById(R.id.Email);
        tvFirstName=findViewById(R.id.FirstName);
        button=findViewById(R.id.chucnang);
        profilePictureView=findViewById(R.id.imgprofile);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        //CallBack gọi đến fb
        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions(Arrays.asList("public_profile","email"));
        button.setVisibility(View.INVISIBLE);
        tvName.setVisibility(View.INVISIBLE);
        tvEmail.setVisibility(View.INVISIBLE);
        tvFirstName.setVisibility(View.INVISIBLE);
button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(Social.this,ChucNangActivityFacebook.class);
        startActivity(intent);
    }
});
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("TAG", "======Facebook login success======");
                Log.d("TAG", "Facebook Access Token: " + loginResult.getAccessToken().getToken());
                Toast.makeText(Social.this, "Login Facebook success.", Toast.LENGTH_SHORT).show();
                button.setVisibility(View.VISIBLE);
                tvName.setVisibility(View.VISIBLE);
                tvEmail.setVisibility(View.VISIBLE);
                tvFirstName.setVisibility(View.VISIBLE);
                getFbInfo();
            }

            @Override
            public void onCancel() {
                Toast.makeText(Social.this, "Login Facebook cancelled.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Log.e("TAG", "======Facebook login error======");
                Log.e("TAG", "Error: " + error.toString());
                Toast.makeText(Social.this, "Login Facebook error.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void getFbInfo() {
        if (AccessToken.getCurrentAccessToken() != null) {
            GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(final JSONObject me, GraphResponse response) {
                    if (me != null) {
                        Log.d("JSON",response.getJSONObject().toString());
                        try {
                            email=me.getString("email");
                            name=me.getString("name");
                            firstname=me.getString("first_name");
                            profilePictureView.setProfileId(Profile.getCurrentProfile().getId());
                            tvEmail.setText(email);
                            tvName.setText(name);
                            tvFirstName.setText(firstname);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

            });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "name,email,first_name");
            request.setParameters(parameters);
            request.executeAsync();
        }
    }


}