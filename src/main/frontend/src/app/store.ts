import { combineReducers, configureStore } from '@reduxjs/toolkit';
import { persistReducer, persistStore } from 'redux-persist';
import { FLUSH, PAUSE, PERSIST, PURGE, REGISTER, REHYDRATE } from 'redux-persist/es/constants';
import storage from 'redux-persist/lib/storage';
// import authReducer from '../features/auth/authSlice';
// import snackbarReducer from '../features/snackbar/snackbarSlice';
import { mainApi } from '../services/main';


const persistConfig = {
  key: 'root',
  storage,
  //whitelist: ['auth'],
};

const rootReducer = combineReducers({
  //auth: authReducer,
  //snackbar: snackbarReducer,
  [mainApi.reducerPath]: mainApi.reducer,
});

export const store = configureStore({
  reducer: persistReducer(persistConfig, rootReducer),
  middleware: getDefaultMiddleware =>
    getDefaultMiddleware({
      serializableCheck: {
        ignoredActions: [FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER],
      },
    }).concat(mainApi.middleware),
});

export const persistor = persistStore(store);

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
