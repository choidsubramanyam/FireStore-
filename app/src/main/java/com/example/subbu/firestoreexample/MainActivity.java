package com.example.subbu.firestoreexample;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.graphics.drawable.Animatable2Compat;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "response";
    FirebaseFirestore firebaseFirestore;
    boolean isrotated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView imageView=findViewById(R.id.imageView);
        imageView.setImageResource(isrotated?R.drawable.animate_arrow_down :R.drawable.animate_arrow_up);
        TextView textView=findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isrotated = !isrotated;
               //tested
               // imageView.setImageResource(isrotated?R.drawable.arrow_down:R.drawable.arrow_top);
                final AnimatedVectorDrawableCompat drawableCompat= (AnimatedVectorDrawableCompat) imageView.getDrawable();
                drawableCompat.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
                    @Override
                    public void onAnimationEnd(Drawable drawable) {
                        drawableCompat.clearAnimationCallbacks();
                        imageView.setImageResource(isrotated?R.drawable.animate_arrow_down :R.drawable.animate_arrow_up);
                    }
                });
                drawableCompat.start();
//                AnimatedVectorDrawable animatedVectorDrawable= (AnimatedVectorDrawable) imageView.getDrawable();
                /*if (imageView.getDrawable() instanceof Animatable){
                    ((Animatable) imageView.getDrawable()).start();
                }*/
            }
        });
        firebaseFirestore=FirebaseFirestore.getInstance();
        User user=new User(22,"Hyderabad","8184870282","Subramanyam");
        User user1=new User(22,"Nizamabad","8328624128","Subramanyam");
       /* firebaseFirestore.collection("Users").add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });*/
        firebaseFirestore.collection("Users").document("user1").set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });
       /* firebaseFirestore.collection("Users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });*/
        firebaseFirestore.collection("Users").document("user2").set(user1).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
        firebaseFirestore.collection("Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isComplete()){
                    List<User> users=new ArrayList<>();

                    for (DocumentSnapshot df:task.getResult().getDocuments()){
                        users.add(df.toObject(User.class));

                    }
                    for (User user2:users) {
                        Log.e("user", "" + user2.toJson());
                    }

                }

            }
        });
    }
}
