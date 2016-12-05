package com.zhephyr.somedaytoday;

import android.*;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,
        LocationListener, View.OnClickListener{

    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;
    private SignInButton _signInButton;

    private GoogleApiClient _googleApiClient;

    private DatabaseReference _firebaseDatabaseReference;
    private FirebaseAuth _firebaseAuth;
    private FirebaseUser _firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _signInButton = (SignInButton) findViewById(R.id.signin_btn);

        _signInButton.setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestProfile()
                .requestEmail()
                .build();
        _googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addApi(LocationServices.API)
                .build();

        _firebaseAuth = FirebaseAuth.getInstance();
    }

    private void handleFirebaseAuthResult(AuthResult authResult) {
        if (authResult != null) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signin_btn:
                signIn();
                break;
            default:
                return;
        }
    }

    private void signIn() {
        Intent signinIntent = Auth.GoogleSignInApi.getSignInIntent(_googleApiClient);
        startActivityForResult(signinIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                Log.e(TAG, "Google Sign In failed.");
            }
        }
    }

    private void firebaseAuthWithGoogle(final GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle: " + acct.getId());
        AuthCredential cred = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        _firebaseAuth.signInWithCredential(cred)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "signInWithCredential:onComplete: " + task.isSuccessful());

                if (!task.isSuccessful()) {
                    Log.w(TAG, "signInCredential: ", task.getException());
                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    _firebaseDatabaseReference = FirebaseDatabase.getInstance().getReference("users");
                    _firebaseDatabaseReference.orderByChild("userId").equalTo(_firebaseUser.getUid()).addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                return;
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                PackageManager pm = getPackageManager();

                                String fName = acct.getGivenName();
                                String lName = acct.getFamilyName();
                                Date createDate = new Date();
                                if (pm.checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION, getPackageName()) == pm.PERMISSION_GRANTED) {
                                    Location location = LocationServices.FusedLocationApi.getLastLocation(_googleApiClient);
                                } else {
                                    checkPermissions();
                                }
                                String email = acct.getEmail();
//                                startActivity(new Intent(LoginActivity.this, CreateProfileActivity.class));
                            }
                        });
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed: " + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 216);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 216: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    return;
                } else {
                    checkPermissions();
                }
            }
        }
    }
}
