import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";

import { Customer } from "../models/Customer";


const customersTag = "Customers";
const customersPath = "/customers";

export const mainApi = createApi({
  reducerPath: "mainApi",
  baseQuery: fetchBaseQuery({
    baseUrl: process.env.REACT_APP_BASE_URL,
    prepareHeaders: (headers, { getState }) => {

      return headers;
    },
  }),
  tagTypes: [customersTag],
  endpoints: (builder) => ({
    getCustomers: builder.query<Customer[], void>({
      query: () => ({
        url: customersPath,
      }),
      providesTags: [customersTag],
    }),
    editCustomer: builder.mutation<
      null,
      { body: Customer; id: string }
    >({
      query: ({ body, id }) => ({
        url: `${customersPath}/${id}`,
        method: "PUT",
        body,
      }),
      invalidatesTags: [
        customersTag,
      ],
    }),
    deleteCustomer: builder.mutation<
      null,
      { id: string }
    >({
      query: ({ id }) => ({
        url: `${customersPath}/${id}`,
        method: "DELETE",
      }),
      invalidatesTags: [
        customersTag,
      ],
    }),
    addCustomer: builder.mutation<null, Customer>({
      query: (body) => ({
        url: customersPath,
        method: "POST",
        body,
        
      }),
      invalidatesTags: [customersTag],
    }),
  }),
});

export const {
  useGetCustomersQuery,
  useEditCustomerMutation,
  useDeleteCustomerMutation,
  useAddCustomerMutation,
} = mainApi;
