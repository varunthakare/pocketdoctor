import 'package:flutter/material.dart';

class HospitalLoginPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Color(0xFF4A8EFF), // Background color matching the image
      body: Center(
        child: Container(
          padding: EdgeInsets.all(20),
          width: MediaQuery.of(context).size.width * 0.9,
          height: MediaQuery.of(context).size.height * 0.9,
          decoration: BoxDecoration(
            color: Colors.white,
            borderRadius: BorderRadius.circular(20),
          ),
          child: Row(
            children: [
              // Left: Registration Section
              Expanded(
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: [
                    Text(
                      'Hospital Register',
                      style: TextStyle(
                        fontSize: 20,
                        fontWeight: FontWeight.w600,
                        color: Colors.black,
                      ),
                    ),
                    SizedBox(height: 20),
                    _buildTextField('Hospital Name'),
                    SizedBox(height: 15),
                     _buildTextField('Complete Address'),
                    SizedBox(height: 15),
                    _buildAttachmentField('Attach License'),
                    SizedBox(height: 25),
                    _buildTextField('Username'),
                    SizedBox(height: 15),
                    _buildTextField('Password', obscureText: true),
                    SizedBox(height: 15),
                    _buildButton('VERIFY'),
                  ],
                ),
              ),
              // Divider
              VerticalDivider(
                color: Colors.grey.shade300,
                thickness: 1,
                width: 40,
              ),
              // Right: Login Section
              Expanded(
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: [
                    Text(
                      'Hospital Register',
                      style: TextStyle(
                        fontSize: 20,
                        fontWeight: FontWeight.w600,
                        color: Colors.black,
                      ),
                    ),
                    SizedBox(height: 20),
                    _buildTextField('Username'),
                    SizedBox(height: 15),
                    _buildTextField('Password', obscureText: true),
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


  Widget _buildAttachmentField(String label) {
    return Container(
      width: 250,
      padding: EdgeInsets.symmetric(horizontal: 20, vertical: 15),
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(25),
        border: Border.all(color: Colors.grey.shade300),
      ),
      child: Row(
        children: [
          Icon(Icons.add_circle, color: Colors.grey, size: 25),
          SizedBox(width: 10),
          Text(
            label,
            style: TextStyle(
              color: Colors.grey.shade500,
              fontSize: 16,
            ),
          ),
        ],
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
    home: HospitalLoginPage(),
  ));
}
