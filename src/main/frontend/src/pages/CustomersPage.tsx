import { FetchBaseQueryError } from "@reduxjs/toolkit/query";
import { randomId } from "@mui/x-data-grid-generator";

import { Customer } from "../models/Customer";
import { CustomersTable } from "../components/CustomersTable";
import { useGetCustomersQuery } from "../services/main";
import { useAppDispatch } from "../hooks/reduxCommon";
import {
  useAddCustomerMutation,
  useEditCustomerMutation,
  useDeleteCustomerMutation,
} from "../services/main";
import { showError } from "../features/snackbar/snackbarSlice";
import { latLngEquals } from "@vis.gl/react-google-maps";

export const CustomersPage = () => {
  const { data } = useGetCustomersQuery();
  const [addCustomer] = useAddCustomerMutation();
  const [editCustomer] = useEditCustomerMutation();
  const [deleteCustomer] = useDeleteCustomerMutation();

  const dispatch = useAppDispatch();

  const handleEditCustomer = async (
    id: string,
    firstName: string,
    lastName: string,
    company: string,
    city: string,
    street: string,
    phoneNumber: string,
    emailAddress: string,
    lastOverviewDate: string,
    lat: number,
    lng: number
  ) => {
    const result: any = await editCustomer({
      body: {
        id: id,
        firstName: firstName,
        lastName: lastName,
        company: company,
        city: city,
        street: street,
        phoneNumber: phoneNumber,
        emailAddress: emailAddress,
        lastOverviewDate: lastOverviewDate,
        lat: lat,
        lng: lng,
      },
      id: id,
    });
  };

  const handleAddCustomer = async (
    id: string,
    firstName: string,
    lastName: string,
    company: string,
    city: string,
    street: string,
    phoneNumber: string,
    emailAddress: string,
    lastOverviewDate: string,
    lat: number,
    lng: number
  ) => {
    const result: any = await addCustomer({
      id: id,
      firstName: firstName,
      lastName: lastName,
      company: company,
      city: city,
      street: street,
      phoneNumber: phoneNumber,
      emailAddress: emailAddress,
      lastOverviewDate: lastOverviewDate,
      lat: lat,
      lng: lng,
    });
  };

  const handleDeleteCustomer = async (id: string) => {
    const result: any = await deleteCustomer({ id: id });
  };

  return (
    <CustomersTable
      data={data || []}
      onEditCustomer={handleEditCustomer}
      onAddCustomer={handleAddCustomer}
      onDeleteCustomer={handleDeleteCustomer}
    />
  );
};
