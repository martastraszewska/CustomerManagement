import { createSlice, PayloadAction } from '@reduxjs/toolkit';

interface SnackbarContextType {
  message: string;
}

const initialState: SnackbarContextType = {
  message: '',
};

const snackbarSlice = createSlice({
  name: 'snackbar',
  initialState,
  reducers: {
    showError: (state, action: PayloadAction<string>) => {
      state.message = action.payload;
    },
  },
});

export const { showError } = snackbarSlice.actions;
export default snackbarSlice.reducer;
