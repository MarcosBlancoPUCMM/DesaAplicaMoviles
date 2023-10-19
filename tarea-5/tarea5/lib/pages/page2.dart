import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

class Page2 extends StatefulWidget {
  const Page2({super.key});

  @override
  State<Page2> createState() => _Page2State();
}

class _Page2State extends State<Page2> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Rows & Columns'),
        centerTitle: true,
        backgroundColor: Colors.lightBlueAccent.shade100,
      ),
      backgroundColor: Colors.white,
      body: Column(
        children: [
          Expanded(
              child: Row(
                children: [
                  Expanded(
                      child: Container(
                          margin: const EdgeInsets.only(
                            top: 20,
                            bottom: 10,
                            left: 20,
                            right: 10,
                          ),
                          decoration: BoxDecoration(
                            color: Colors.lightBlueAccent.shade100,
                            borderRadius: BorderRadius.circular(10),
                          )
                      )
                  ),
                  Expanded(
                      flex: 2,
                      child: Column(
                        children: [
                          Expanded(
                              flex: 2,
                              child: Container(
                                  margin: const EdgeInsets.only(
                                    top: 20,
                                    bottom: 10,
                                    left: 10,
                                    right: 20,
                                  ),
                                  height: double.infinity,
                                  width: double.infinity,
                                  child: ClipRRect(
                                    borderRadius: BorderRadius.circular(10),
                                    child: Image.asset(
                                        'lib/images/biggerMillie.jpg',
                                        fit: BoxFit.cover
                                    ),
                                  )
                              )
                          ),
                          Expanded(
                              child: Container(
                                margin: const EdgeInsets.only(
                                  top: 10,
                                  bottom: 10,
                                  left: 10,
                                  right: 20,
                                ),
                                decoration: BoxDecoration(
                                  borderRadius: BorderRadius.circular(10),
                                  border: Border.all(
                                    color: Colors.lightBlueAccent.shade100,
                                    width: 2,
                                  ),
                                ),
                              )
                          ),
                        ],
                      )
                  )
                ],
              )
          ),
          Expanded(
              child: Container(
                  margin: const EdgeInsets.only(
                    top: 10,
                    bottom: 10,
                    left: 20,
                    right: 20,
                  ),
                  height: double.infinity,
                  width: double.infinity,
                  child: ClipRRect(
                    borderRadius: BorderRadius.circular(10),
                    child: Image.asset(
                        'lib/images/20170326_090245.jpg',
                        fit: BoxFit.cover
                    ),
                  )
              )
          ),
          Expanded(
              child: Row(
                children: [
                  Expanded(
                    flex: 2,
                    child: Container(
                      margin: const EdgeInsets.only(
                        top: 10,
                        bottom: 20,
                        left: 20,
                        right: 10,
                      ),
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(10),
                        border: Border.all(
                          color: Colors.lightBlueAccent.shade100,
                          width: 2,
                        ),
                      ),
                      height: double.infinity,
                      width: double.infinity,
                      alignment: Alignment.center,
                      child: Text('Millie',
                        style: GoogleFonts.lobster(
                          fontSize: 48,
                          color: Colors.lightBlueAccent.shade100,
                        ),
                      ),
                    ),
                  ),
                  Expanded(
                      child: Container(
                          margin: const EdgeInsets.only(
                            top: 10,
                            bottom: 20,
                            left: 10,
                            right: 20,
                          ),
                          height: double.infinity,
                          width: double.infinity,
                          child: ClipRRect(
                            borderRadius: BorderRadius.circular(10),
                            child: Image.asset(
                                'lib/images/20160703_225013.jpg',
                                fit: BoxFit.cover
                            ),
                          )
                      )
                  )
                ],
              )
          ),
        ],
      ),
    );
  }
}
