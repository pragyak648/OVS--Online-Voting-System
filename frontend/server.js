// OTP handling file for admin

const express = require("express");
const bodyParser = require("body-parser");
const nodemailer = require("nodemailer");
const cors = require("cors");
const bcrypt = require("bcrypt");

const app = express();
app.use(cors());
app.use(bodyParser.json());

// Mock admin data (in real system, use a DB)
const ADMIN = {
  username: "admin",
  // Hash the password once and store it (StrongPassword123 -> hashed)
  passwordHash: bcrypt.hashSync("StrongPassword123", 10),
  email: "admin@example.com"
};

// Store OTP & expiry
let otpStore = {
  code: null,
  expiry: null
};

// Mail transporter (Gmail example, replace with your SMTP)
const transporter = nodemailer.createTransport({
  service: "gmail",
  auth: {
    user: "youremail@gmail.com",        // your email
    pass: "your-app-password"           // your app password
  }
});

// Step 1: Verify Username + Password, then send OTP
app.post("/admin/login", async (req, res) => {
  const { username, password } = req.body;

  if (username !== ADMIN.username) {
    return res.status(401).send("Invalid credentials");
  }

  const isMatch = await bcrypt.compare(password, ADMIN.passwordHash);
  if (!isMatch) {
    return res.status(401).send("Invalid credentials");
  }

  // Generate 6-digit OTP
  const otp = Math.floor(100000 + Math.random() * 900000).toString();
  otpStore.code = otp;
  otpStore.expiry = Date.now() + 5 * 60 * 1000; // expires in 5 minutes

  // Send OTP via email
  const mailOptions = {
    from: "youremail@gmail.com",
    to: ADMIN.email,
    subject: "Your Admin Login OTP",
    text: `Your OTP is: ${otp}. It will expire in 5 minutes.`
  };

  transporter.sendMail(mailOptions, (err, info) => {
    if (err) {
      console.error("Error sending OTP email:", err);
      return res.status(500).send("Error sending OTP.");
    }
    console.log("OTP sent: " + otp);
    res.send("Password OK. OTP sent.");
  });
});

// Step 2: Verify OTP
app.post("/admin/verify-otp", (req, res) => {
  const { otp } = req.body;

  if (!otpStore.code || Date.now() > otpStore.expiry) {
    otpStore = { code: null, expiry: null }; // clear expired
    return res.status(401).send("Invalid or expired OTP");
  }

  if (otp !== otpStore.code) {
    return res.status(401).send("Invalid or expired OTP");
  }

  // OTP valid → clear it
  otpStore = { code: null, expiry: null };

  res.send("Login successful");
});

// Start server
app.listen(8080, () => {
  console.log("Server running on http://localhost:8080");
});
