import React, { PropsWithChildren } from 'react';

import { Alert, Container, Snackbar } from '@mui/material';

import { RootState } from '../app/store';
import { showError } from '../features/snackbar/snackbarSlice';
import { useAppDispatch, useAppSelector } from '../hooks/reduxCommon';

export const GeneralPage = ({ children }: PropsWithChildren) => {
  const snackbarMessage: string = useAppSelector((state: RootState) => state.snackbar.message);
  const dispatch = useAppDispatch();

  const handleSnackbarClose = () => dispatch(showError(''));

  return (
    <Container sx={{ textAlign: 'center', my: '1rem' }}>
      {children}

      <Snackbar
        open={Boolean(snackbarMessage)}
        autoHideDuration={6000}
        onClose={handleSnackbarClose}>
        <Alert
          onClose={handleSnackbarClose}
          severity='error'
          variant='filled'
          sx={{ width: '100%' }}
        >
          {snackbarMessage}
        </Alert>
      </Snackbar>
    </Container>
  );
};
