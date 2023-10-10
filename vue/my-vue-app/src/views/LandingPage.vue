<template>
  <div class="container">
    <div class="left-landing-page">
        <img class="landing-page-pic" src="@/assets/images/car.jpg" alt="Car Image">
    </div>

    <div class="right-landing-page">
        <p id="zero">&#183;&#160;Hole Story&#160;&#183;</p>
        <p id="one">Reporting Potholes &#183; Restoring Roads</p>
        <p id="two" v-if="!showLoginForm">Sign up today!</p>
        <p id="two" v-else>Welcome back! Sign in here:</p>

        <div class="sign-up-container">
            <form v-if="!showLoginForm" @submit.prevent="register">
                <div class="flex-container">
                    <input type="text" v-model="first_name" name="first_name" placeholder="First Name" required>
                    <input type="text" v-model="last_name" name="last_name" placeholder="Last Name" required>
                </div>

                <div class="flex-container">
                    <input type="email" v-model="email" name="email" placeholder="Email" required>
                    <input type="tel" v-model="phone" name="phone" placeholder="Phone Number" required>
                </div>

                <div class="flex-container">
                    <input type="password" v-model="password" name="password" placeholder="Password" required>
                    <input type="password" v-model="confirmPassword" name="confirmPassword" placeholder="Confirm Password" required>
                </div>

                <button type="submit">Register</button>
                
                <p v-if="!showLoginForm" id="haveAccountText">
                    <span class="signup-text">Already have an account?</span>&nbsp;
                    <a href="#" class="signin-link" @click.prevent="toggleForms">Sign in here!</a>
                </p>
            </form>

            <form v-if="showLoginForm" @submit.prevent="login">
                <div class="flex-container">
                    <input type="text" v-model="login_username" name="login_username" placeholder="Username" required>
                    <input type="password" v-model="login_password" name="login_password" placeholder="Password" required>
                </div>

                <button type="submit">Sign In</button>
            </form>
            
            <p v-if="showLoginForm" id="dontHaveAccountText">
                <span class="signup-text">Don't have an account?</span>&nbsp;
                <a href="#" class="signin-link" @click.prevent="toggleForms">Sign up here!</a>
            </p>
        </div>       
    </div>
  </div>
</template>

<script>
import apiService from '@/services/apiService.js';

export default {
  data() {
    return {
      showLoginForm: false,
      first_name: '',
      last_name: '',
      email: '',
      phone: '',
      // username: '',
      password: '',
      confirmPassword: '',
      login_username: '',
      login_password:''
    };
  },
  methods: {
    toggleForms() {
      this.showLoginForm = !this.showLoginForm;
    },
    async register() {
  if (this.password !== this.confirmPassword) {
    console.error("Passwords do not match!");
    return;
  }

  const userCredentials = {
        first_name: this.first_name,
        last_name: this.last_name,
        email: this.email,
        phone: this.phone,
        // username: this.username,
        password: this.password,
      };
      
      try {
        const response = await apiService.registerUser(userCredentials);
        if(response.status === 201) {
          // handle success, perhaps redirect to login or show a success message
          console.log("User registered successfully!");
          this.$router.push({ name: 'accountSetup' });
        }
      } catch (error) {
        console.error("Error during registration:", error.response ? error.response.data : error.message);
        // handle error, perhaps show an error message to the user
      }
    },

    async login() {
      const loginCredentials = {
        username: this.login_username,
        password: this.login_password,
      };

      try {
        const response = await apiService.loginUser(loginCredentials);
        if(response.status === 200) {
          // Here you store the JWT token. For simplicity, I'm using localStorage. Consider other methods for production.
          localStorage.setItem('token', response.data);
          console.log("User logged in successfully!");
          // Redirect to the main page or dashboard
        }
      } catch (error) {
        console.error("Error during login:", error.response ? error.response.data : error.message);
        // handle error, perhaps show an error message to the user
      }
    }
  },
};
</script>

<style scoped>
  @import url('https://fonts.googleapis.com/css2?family=Young+Serif&display=swap');
  @import '@/assets/css/landing-page-style.css';
</style>

  