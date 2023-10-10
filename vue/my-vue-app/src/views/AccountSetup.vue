<template>
    <div>
      <div class="setup-container">
        <!-- Username -->
        <div class="username-section"> 
            <input type="text" v-model="username" placeholder="Choose a Username">
            <span v-if="usernameChecked">
                <span v-if="usernameAvailable" class="available">✔ Username available</span>
                <span v-else class="not-available">✖ Username not available</span>
            </span>
        </div>

        <!-- Profile Picture -->
        <input type="file" @change="onImageChange">
        
        <!-- Vehicle Info -->
        <input type="text" v-model="vehicleInfo" placeholder="Enter Vehicle Information">
        
        <!-- Submit Button -->
        <button @click="completeAccountSetup">Complete Setup</button>
      </div>
    </div>
</template>

<script>
import { debounce } from "lodash";
import apiService from "@/services/apiService";

export default {
  data() {
    return {
      username: '',
      profilePic: null,
      vehicleInfo: '',
      usernameAvailable: null,
      usernameChecked: false
    };
  },
  methods: {
    async checkUsernameAvailability() {
      this.usernameChecked = true;  // This line is added to ensure the checkmark or cross is shown
      try {
          const response = await apiService.checkUsernameAvailability(this.username);
          if (response.data) {
              this.usernameAvailable = true;
          } else {
              this.usernameAvailable = false;
          }
      } catch (error) {
          console.error("Error checking username availability:", error);
      }
    },
    onImageChange(e) {
      let reader = new FileReader();
      reader.readAsDataURL(e.target.files[0]);
      reader.onload = (e) => {
        this.profilePic = e.target.result;
      }
    },
    async completeAccountSetup() {
      if (this.usernameAvailable) {
          const accountSetupData = {
              username: this.username,
              profilePic: this.profilePic,
              vehicleInfo: this.vehicleInfo
          };
          try {
              const response = await apiService.completeAccountSetup(accountSetupData);
              if (response.status === 200) {
                  // Successfully set up the account.
                  // You can redirect to the dashboard or show a success message.
              }
          } catch (error) {
              console.error("Error setting up account:", error);
          }
      } else {
          // Inform the user to select an available username first
      }
    }
  },
  watch: {
    username: debounce(function(newVal) {
        if (newVal) {
            this.checkUsernameAvailability();
        } else {
            this.usernameChecked = false;
        }
    }, 500)
  }
}
</script>

<style scoped>
@import '@/assets/css/account-setup-style.css';
</style>
