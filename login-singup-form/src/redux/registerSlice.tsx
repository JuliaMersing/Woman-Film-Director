import { createSlice, PayloadAction } from '@reduxjs/toolkit';

type newUserType = {
  email: string;
  password: string;
}

const initialState = {
  email: '',
  password: '',
};

export const registerSlice = createSlice({
  name: 'register',
  initialState,
  reducers: {
    newUser: (state, { payload }: PayloadAction<newUserType>) => {
      state.email = payload.email;
      state.password = payload.password;
    },
  },
});

export const { newUser } = registerSlice.actions;

export default registerSlice.reducer;
