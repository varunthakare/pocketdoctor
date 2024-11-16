import 'package:flutter/material.dart';

class DocterLoginPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Color(0xFF4A8EFF), // Background color matching the image
      body: Center(
        child: Container(
          padding: EdgeInsets.all(20),
          width: MediaQuery.of(context).size.width * 0.4,
          height: MediaQuery.of(context).size.height * 0.6,
          decoration: BoxDecoration(
            color: Colors.white,
            borderRadius: BorderRadius.circular(20),
          ),
          child: Row(
            children: [
              // Left: Registration Section
              // Expanded(
              //   child: Column(
              //     mainAxisAlignment: MainAxisAlignment.center,
              //     crossAxisAlignment: CrossAxisAlignment.center,
              //     children: [
              //       Text(
              //         'Docter Register',
              //         style: TextStyle(
              //           fontSize: 20,
              //           fontWeight: FontWeight.w600,
              //           color: Colors.black,
              //         ),
              //       ),
              //       SizedBox(height: 20),
              //       _buildTextField('Docter Name'),
              //       SizedBox(height: 15),
              //       _buildTextField('Qualification'),
              //       SizedBox(height: 15),
              //       _buildTextField('Specialist'),
              //       SizedBox(height: 15),
              //       _buildTextField('Hospital ID'),
              //       SizedBox(height: 15),
              //       _buildTextField('Mobile Number'),
              //       SizedBox(height: 15),
              //       _buildTextField('OTP'),
              //       SizedBox(height: 25),
              //       _buildButton('SEND OTP'),
              //     ],
              //   ),
              // ),
              // // Divider
              // VerticalDivider(
              //   color: Colors.grey.shade300,
              //   thickness: 1,
              //   width: 40,
              // ),
              // Right: Login Section
              Expanded(
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: [
                    Text(
                      'Docter Sign in',
                      style: TextStyle(
                        fontSize: 20,
                        fontWeight: FontWeight.w600,
                        color: Colors.black,
                      ),
                    ),
                    SizedBox(height: 20),
                    _buildTextField('Username'),
                    SizedBox(height: 15),
                    _buildTextField('password'),
                    SizedBox(height: 25),
                    _buildButton('SIGN IN'),
                  ],
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }

  Widget _buildTextField(String hint, {bool obscureText = false}) {
  return SizedBox(
    width: 250, // Adjust the width of the text box here
    child: TextField(
      obscureText: obscureText,
      decoration: InputDecoration(
        contentPadding: EdgeInsets.symmetric(horizontal: 20, vertical: 15),
        hintText: hint,
        hintStyle: TextStyle(
          color: Colors.grey.shade500,
        ),
        border: OutlineInputBorder(
          borderRadius: BorderRadius.circular(25),
          borderSide: BorderSide(
            color: Colors.grey.shade300,
          ),
        ),
        enabledBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(25),
          borderSide: BorderSide(
            color: Colors.grey.shade300,
          ),
        ),
        focusedBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(25),
          borderSide: BorderSide(
            color: Colors.blue,
          ),
        ),
      ),
    ),
  );
}

  Widget _buildButton(String label) {
    return SizedBox(
      width: 250,
      child: ElevatedButton(
        onPressed: () {},
        style: ElevatedButton.styleFrom(
          padding: EdgeInsets.symmetric(vertical: 15),
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(25),
          ),
          backgroundColor: Color(0xFF4A8EFF), // Matching button color
        ),
        child: Text(
          label,
          style: TextStyle(
            color: Colors.white,
            fontWeight: FontWeight.bold,
            fontSize: 16,
          ),
        ),
      ),
    );
  }
}

void main() {
  runApp(MaterialApp(
    debugShowCheckedModeBanner: false,
    home: DocterLoginPage(),
  ));
}
