const store = {
  state: {
    user: {
      id: null,
      authorities: [],
      login: '',
      logged: false,
      token: localStorage.getItem('token'),
    },
  },
};

export default store;
