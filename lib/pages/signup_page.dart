import 'package:flutter/material.dart';
import 'signin_page.dart';

class SignUpPage extends StatefulWidget {
  const SignUpPage({super.key});

  @override
  _SignUpPageState createState() => _SignUpPageState();
}

class _SignUpPageState extends State<SignUpPage> {
  // Initial position for animation
  double _bottomPosition = -500;

  @override
  void initState() {
    super.initState();
    // Trigger the animation after the widget is loaded
    Future.delayed(const Duration(milliseconds: 10), () {
      setState(() {
        _bottomPosition = 0; // Move the white block to its final position
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color(0xFF3254ED), // Set background color to blue
      body: Stack(
        children: [
          // Image placed above the white block, in the blue background
          Positioned(
            top: 50, // Adjust the position of the image above the white block
            left: MediaQuery.of(context).size.width * 0.25, // Center the image horizontally
            child: Image.asset(
              'lib/images/signup_img.png', // Replace with your image path
              width: 200, // Width of 200
              height: 200, // Height of 200
            ),
          ),
          // Animated white block
          AnimatedPositioned(
            duration: const Duration(milliseconds: 500),
            bottom: _bottomPosition, // Move from bottom to top
            left: 0,
            right: 0,
            child: Container(
              height: MediaQuery.of(context).size.height * 0.7, // Cover 70% of the screen
              padding: const EdgeInsets.all(20),
              decoration: BoxDecoration(
                color: Colors.white,
                borderRadius: const BorderRadius.only(
                  topLeft: Radius.circular(40),
                  topRight: Radius.circular(40),
                ),
              ),
              child: SingleChildScrollView( // Add scroll functionality
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: [
                    const SizedBox(height: 40),
                    const Text(
                      'Sign up to continue',
                      style: TextStyle(
                        fontSize: 32,
                        fontWeight: FontWeight.bold,
                        color: Colors.black,
                      ),
                    ),
                    const SizedBox(height: 30),
                    // Name field
                    TextField(
                      decoration: InputDecoration(
                        labelText: 'Name',
                        border: OutlineInputBorder(
                          borderSide: BorderSide(
                            color: const Color(0xFF3254ED), // Border color same as background
                          ),
                        ),
                        contentPadding: const EdgeInsets.symmetric(
                            vertical: 12, horizontal: 16),
                      ),
                    ),
                    const SizedBox(height: 20),
                    // Email field
                    TextField(
                      decoration: InputDecoration(
                        labelText: 'Email',
                        border: OutlineInputBorder(
                          borderSide: BorderSide(
                            color: const Color(0xFF3254ED), // Border color same as background
                          ),
                        ),
                        contentPadding: const EdgeInsets.symmetric(
                            vertical: 12, horizontal: 16),
                      ),
                    ),
                    const SizedBox(height: 20),
                    // Mobile Number field
                    TextField(
                      decoration: InputDecoration(
                        labelText: 'Mobile Number',
                        border: OutlineInputBorder(
                          borderSide: BorderSide(
                            color: const Color(0xFF3254ED), // Border color same as background
                          ),
                        ),
                        contentPadding: const EdgeInsets.symmetric(
                            vertical: 12, horizontal: 16),
                      ),
                    ),
                    const SizedBox(height: 20),
                    // OTP field
                    TextField(
                      obscureText: true,
                      decoration: InputDecoration(
                        labelText: 'OTP',
                        border: OutlineInputBorder(
                          borderSide: BorderSide(
                            color: const Color(0xFF3254ED), // Border color same as background
                          ),
                        ),
                        contentPadding: const EdgeInsets.symmetric(
                            vertical: 12, horizontal: 16),
                      ),
                    ),
                    const SizedBox(height: 30),
                    // Sign Up button
                    Center(
                      child: ElevatedButton(
                        onPressed: () {
                          // Handle Sign Up action
                        },
                        style: ElevatedButton.styleFrom(
                          padding: const EdgeInsets.symmetric(
                              vertical: 12, horizontal: 100), // Increased width
                          backgroundColor: const Color(0xFF3254ED), // Background color same as page
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(5),
                          ),
                        ),
                        child: const Text(
                          'SIGN UP',
                          style: TextStyle(fontSize: 18, color: Colors.white), // White text color
                        ),
                      ),
                    ),
                    const SizedBox(height: 20),
                    // "Already have an account?" text
                    Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        const Text(
                          "Already have an account? ",
                          style: TextStyle(
                            fontSize: 16,
                            color: Colors.black,
                          ),
                        ),
                        GestureDetector(
                          onTap: () {
                            // Navigate to SignInPage when Sign In is clicked
                            Navigator.push(
                              context,
                              MaterialPageRoute(builder: (context) => const SignInPage()),
                            );
                          },
                          child: const Text(
                            'Sign In',
                            style: TextStyle(
                              fontSize: 16,
                              color: Color(0xFF3254ED),
                            ),
                          ),
                        ),
                      ],
                    ),
                    const SizedBox(height: 20),
                    // Horizontal line with light grey color and shorter width
                    const Divider(
                      color: Color(0xFFB0BEC5),
                      thickness: 1,
                      indent: 100,
                      endIndent: 100,
                    ),
                    const SizedBox(height: 20),
                    // Continue with Google button
                    TextButton.icon(
                      onPressed: () {
                        // Handle Google Sign-In action
                      },
                      icon: Image.asset(
                        'lib/images/googleicon_img.png', // Replace with your Google icon path
                        width: 24,
                        height: 24,
                      ),
                      label: const Text(
                        'Continue with Google',
                        style: TextStyle(
                          fontSize: 16,
                          color: Color(0xFF3254ED),
                        ),
                      ),
                      style: TextButton.styleFrom(
                        padding: const EdgeInsets.symmetric(vertical: 12, horizontal: 40),
                        backgroundColor: Colors.white,
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(5),
                          side: BorderSide(
                            color: const Color(0xFF3254ED),
                          ),
                        ),
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }
}
