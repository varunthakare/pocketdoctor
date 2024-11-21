import 'package:flutter/material.dart';

class HospitalDashPage extends StatelessWidget {
  final Map<String, dynamic> hospitalData;

  const HospitalDashPage({Key? key, required this.hospitalData}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    // Retrieve the doctors list from the hospital data
    final List<dynamic> doctors = hospitalData['doctors'] ?? [];

    return Scaffold(
      appBar: AppBar(
        title: Text(
          hospitalData['name'] ?? 'Hospital Name',
          style: const TextStyle(fontSize: 20),
        ),
        centerTitle: true,
        backgroundColor: Colors.blue,
      ),
      body: doctors.isEmpty
          ? Center(child: Text('No doctors available for this hospital'))
          : ListView.builder(
              padding: const EdgeInsets.all(8.0),
              itemCount: doctors.length,
              itemBuilder: (context, index) {
                final doctor = doctors[index];
                return createDoctorCard(
                  context,
                  doctorImageUrl: doctor['imageUrl'] ?? 'lib/images/profile.png',
                  doctorName: doctor['name'] ?? 'Unknown',
                  doctorQualification: doctor['qualification'] ?? 'Unknown',
                  doctorSpeciality: doctor['speciality'] ?? 'Unknown',
                );
              },
            ),
    );
  }

  Widget createDoctorCard(
    BuildContext context, {
    required String doctorImageUrl,
    required String doctorName,
    required String doctorQualification,
    required String doctorSpeciality,
  }) {
    return Card(
      margin: const EdgeInsets.symmetric(vertical: 8.0),
      elevation: 4.0,
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(12.0),
      ),
      child: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Row(
          children: [
            CircleAvatar(
              radius: 40,
              backgroundImage: AssetImage(doctorImageUrl),
            ),
            const SizedBox(width: 16.0),
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    doctorName,
                    style: const TextStyle(
                      fontSize: 18.0,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  const SizedBox(height: 4.0),
                  Text(
                    doctorQualification,
                    style: const TextStyle(fontSize: 14.0, color: Colors.grey),
                  ),
                  const SizedBox(height: 4.0),
                  Text(
                    doctorSpeciality,
                    style: const TextStyle(fontSize: 14.0, color: Colors.grey),
                  ),
                ],
              ),
            ),
            ElevatedButton.icon(
              onPressed: () {
                // Handle appointment logic here
              },
              icon: const Icon(Icons.calendar_today, size: 16),
              label: const Text('Get Appointment'),
              style: ElevatedButton.styleFrom(
                backgroundColor: Colors.blue,
                foregroundColor: Colors.white,
                elevation: 4.0,
                shadowColor: Colors.blueAccent,
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(8.0),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
