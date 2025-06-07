package com.nitin.registration;



import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText etFirstName, etLastName, etMobileNumber, etEmail;
    private Button btnDob, btnSubmit;
    private TextView tvDobDisplay;
    private Switch switchOlderThan18, switchPrivacyPolicy;

    private Calendar calendar;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        etFirstName = findViewById(R.id.et_first_name);
        etLastName = findViewById(R.id.et_last_name);
        etMobileNumber = findViewById(R.id.et_mobile_number);
        etEmail = findViewById(R.id.et_email);

        btnDob = findViewById(R.id.btn_dob);
        tvDobDisplay = findViewById(R.id.tv_dob_display);

        switchOlderThan18 = findViewById(R.id.switch_older_than_18);
        switchPrivacyPolicy = findViewById(R.id.switch_privacy_policy);

        btnSubmit = findViewById(R.id.btn_submit);

        // DOB picker setup
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(MainActivity.this,
                (DatePicker view, int selectedYear, int selectedMonth, int selectedDay) -> {
                    String dobText = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                    tvDobDisplay.setText(dobText);
                }, year, month, day);

        btnDob.setOnClickListener(v -> datePickerDialog.show());

        btnSubmit.setOnClickListener(v -> {
            boolean isValid = true;

            // Validate First Name
            if (etFirstName.getText().toString().trim().isEmpty()) {
                etFirstName.setError("First name is required");
                isValid = false;
            }

            // Validate Last Name
            if (etLastName.getText().toString().trim().isEmpty()) {
                etLastName.setError("Last name is required");
                isValid = false;
            }

            // Validate Mobile Number
            String mobile = etMobileNumber.getText().toString().trim();
            if (mobile.isEmpty()) {
                etMobileNumber.setError("Mobile number is required");
                isValid = false;
            } else if (mobile.length() != 10) {
                etMobileNumber.setError("Enter valid 10-digit mobile number");
                isValid = false;
            }

            // Validate Email
            String email = etEmail.getText().toString().trim();
            if (email.isEmpty()) {
                etEmail.setError("Email is required");
                isValid = false;
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.setError("Enter a valid email");
                isValid = false;
            }

            // Validate DOB
            if (tvDobDisplay.getText().toString().equals("No Date Selected")) {
                tvDobDisplay.setError("Select your Date of Birth");
                Toast.makeText(this, "Please select your Date of Birth", Toast.LENGTH_SHORT).show();
                isValid = false;
            } else {
                tvDobDisplay.setError(null); // clear error if set
            }

            // Validate switches
            if (!switchOlderThan18.isChecked()) {
                switchOlderThan18.setError("Confirm you're older than 18");
                Toast.makeText(this, "Please confirm you're older than 18", Toast.LENGTH_SHORT).show();
                isValid = false;
            } else {
                switchOlderThan18.setError(null);
            }

            if (!switchPrivacyPolicy.isChecked()) {
                switchPrivacyPolicy.setError("Accept privacy policy");
                Toast.makeText(this, "Please accept the privacy policy", Toast.LENGTH_SHORT).show();
                isValid = false;
            } else {
                switchPrivacyPolicy.setError(null);
            }

            if (!isValid) return;

            // Send data to next screen
            Intent intent = new Intent(MainActivity.this, Second2.class);
            intent.putExtra("Firstname", etFirstName.getText().toString().trim());
            intent.putExtra("Lastname", etLastName.getText().toString().trim());
            intent.putExtra("Mobile", mobile);
            intent.putExtra("Email", email);
            intent.putExtra("DOB", tvDobDisplay.getText().toString());

            startActivity(intent);
        });
    }
}
