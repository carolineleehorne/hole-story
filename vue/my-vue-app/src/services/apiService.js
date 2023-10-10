import axios from 'axios';

const apiClient = axios.create({
  baseURL: 'http://localhost:8081', 
  withCredentials: false, 
  headers: {
    Accept: 'application/json',
    'Content-Type': 'application/json'
  }
});

apiClient.interceptors.request.use(
  (config) => {
    // List of routes that don't need the JWT
    const noAuthRoutes = ['/api/users/register', '/api/users/check-username'];

    if (!noAuthRoutes.includes(config.url)) {
      const token = localStorage.getItem('token');
      if (token) {
        config.headers.Authorization = `Bearer ${token}`;
      }
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);



export default {
  registerUser(credentials) {
    return apiClient.post('/api/users/register', credentials);
  },
  
  loginUser(credentials) {
    return apiClient.post('/api/auth/login', credentials);
  },
  
  checkUsernameAvailability(username) {
    return apiClient.get('/api/users/check-username', {
      params: { username }
    });
  },

  completeAccountSetup(accountSetupData) {
    return apiClient.post('/api/users/setup-account', accountSetupData);
  }
};
