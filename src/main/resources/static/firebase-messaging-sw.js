// This is the background worker for Firebase Cloud Messaging
importScripts('https://www.gstatic.com/firebasejs/9.23.0/firebase-app-compat.js');
importScripts('https://www.gstatic.com/firebasejs/9.23.0/firebase-messaging-compat.js');

// IMPORTANT: You MUST paste your firebaseConfig here as well!
const firebaseConfig = {
		  	apiKey: "AIzaSyCqVUphW2wMGZqXFFYdgdBDQkGCzEb1nzc",
 			authDomain: "kafka-notification-system.firebaseapp.com",
  			projectId: "kafka-notification-system",
  			storageBucket: "kafka-notification-system.firebasestorage.app",
  			messagingSenderId: "640226249625",
  			appId: "1:640226249625:web:1873200d7625a5c54f2108",
  			measurementId: "G-D1J3XKY14B"
		};

firebase.initializeApp(firebaseConfig);
const messaging = firebase.messaging();

console.log('Firebase Service Worker has been initialized');
