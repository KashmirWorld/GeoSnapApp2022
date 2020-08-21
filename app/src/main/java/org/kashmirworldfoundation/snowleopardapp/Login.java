<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/LoginLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@drawable/background"
    tools:context=".Login">
    <ImageView

        android:id="@+id/BackgroundLogin"
        android:layout_width="411dp"
        android:layout_height="735dp"
        tools:layout_editor_absoluteX="0dp"
        tools:layout_editor_absoluteY="-5dp"
        tools:srcCompat="@tools:sample/backgrounds/scenic"
        android:visibility="invisible"
        android:scaleType="fitXY"/>
    <TextView
        android:id="@+id/RegisterOrgBtn"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="New Organization? Register Here"
        android:textColor="#FAF5F7"
        android:textSize="18sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.84000003" />

    <TextView
        android:id="@+id/Register0"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="New Member? Register Here"
        android:textColor="#FAF5F7"
        android:textSize="18sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.758" />

    <Button
        android:id="@+id/LoginBtn"
        android:layout_width="362dp"
        android:layout_height="55dp"
        android:background="#FFFF8D"
        android:fontFamily="@font/aclonica"
        android:text="LOGIN"
        android:textColor="#1B1A1A"
        android:textSize="24sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.673"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/password"
        app:layout_constraintVertical_bias="0.214" />

    <EditText
        android:id="@+id/password"
        android:layout_width="375dp"
        android:layout_height="42dp"
        android:backgroundTint="#FAFAFA"
        android:ems="10"
        android:fontFamily="@font/aclonica"
        android:hint="Password:"
        android:inputType="textPassword"
        android:textColorHint="#F8F8F8"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <EditText
        android:id="@+id/email"
        android:layout_width="375dp"
        android:layout_height="42dp"
        android:backgroundTint="#FAFAFA"
        android:ems="10"
        android:fontFamily="@font/aclonica"
        android:hint="Email:"
        android:inputType="textPersonName"
        android:textColorHint="#F8F8F8"
        app:layout_constraintBottom_toTopOf="@+id/password"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView3" />

    <TextView
        android:id="@+id/textView3"
        android:layout_width="66dp"
        android:layout_height="32dp"
        android:elegantTextHeight="true"
        android:fontFamily="@font/aclonica"
        android:shadowColor="#B4B1B1"
        android:text="Login"
        android:textColor="#FFFF8D"
        android:textSize="18sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.26999998" />

    <TextView
        android:id="@+id/textView"
        android:layout_width="288dp"
        android:layout_height="45dp"
        android:elegantTextHeight="true"
        android:fontFamily="@font/aclonica"
        android:shadowColor="#B4B1B1"
        android:text="Snow Leopard"
        android:textColor="#FFFFFF"
        android:textSize="36sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.495"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.19999999" />

    <ProgressBar
        android:id="@+id/progressBar"
        style="?android:attr/progressBarStyle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:visibility="invisible"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.498"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.92" />



</androidx.constraintlayout.widget.ConstraintLayout>
