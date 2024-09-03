import { useEffect, useState } from "react";
import { DataGrid, GridColDef } from "@mui/x-data-grid";
import { Button, ButtonGroup, Box } from "@mui/material";
import Paper from "@mui/material/Paper";
import TableContainer from "@mui/material/TableContainer";
import { CustomerAddDialog } from "./CustomerAddDialog";
import { CustomerEditDialog } from "./CustomerEditDialog";
import { GridRowParams } from "@mui/x-data-grid";
import { Customer } from "../models/Customer";
import { CustomerDeleteDialog } from "./CustomerDeleteDialog";
import { CustomerDetailsDialog } from "./CustomerDetailsDialog";
import { MapDialog } from "./MapDialog";

interface CustomersTableProps {
  data: Customer[];
  onEditCustomer: (
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
  ) => void;
  onAddCustomer: (
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
  ) => void;
  onDeleteCustomer: (id: string) => void;
}

interface Row {
  id: string;
  firstName: string;
  lastName: string;
  company: string;
  city: string;
  street: string;
  phoneNumber: string;
  emailAddress: string;
  lastOverviewDate: string;
  lat: number;
  lng: number;
}
export const CustomersTable = ({
  data,
  onAddCustomer,
  onEditCustomer,
  onDeleteCustomer,
}: CustomersTableProps) => {
  const [rows, setRows] = useState<Row[]>([]);
  const [addDialogOpened, setAddDialogOpened] = useState(false);
  const [editDialogOpened, setEditDialogOpened] = useState(false);
  const [currentRow, setCurrentRow] = useState({
    id: "",
    firstName: "",
    lastName: "",
    company: "",
    city: "",
    street: "",
    phoneNumber: "",
    emailAddress: "",
    lastOverviewDate: "",
    lat: 0,
    lng: 0,
  });
  const [deleteDialogOpened, setDeleteDialogOpened] = useState(false);
  const [detailsDialogOpened, setDetailsDialogOpened] = useState(false);
  const [mapDialogOpened, setMapDialogOpened] = useState(false);

  useEffect(() => {
    setRows(createData(data));
  }, [data]);

  function createData(customers: Customer[]) {
    return (
      customers.map((record) => {
        return {
          id: record.id,
          firstName: record.firstName,
          lastName: record.lastName,
          company: record.company,
          city: record.city,
          street: record.street,
          phoneNumber: record.phoneNumber,
          emailAddress: record.emailAddress,
          lastOverviewDate: record.lastOverviewDate,
          lat: record.lat,
          lng: record.lng,
        };
      }) || []
    );
  }

  const handleEditButtonClicked = (row: Row) => {
    setCurrentRow(row);
    setEditDialogOpened(true);
  };

  const handleAddButtonClicked = () => {
    setAddDialogOpened(true);
  };

  const handleDeleteButtonClicked = (row: Row) => {
    setCurrentRow(row);
    setDeleteDialogOpened(true);
  };

  const handleDetailsButtonClicked = (row: Row) => {
    setCurrentRow(row);
    setDetailsDialogOpened(true);
  };
  const handleSeeMapButtonClicked = () => {
    setMapDialogOpened(true);
  };

  const columns: GridColDef[] = [
    { field: "firstName", headerName: "Imie" },
    { field: "lastName", headerName: "Nazwisko" },
    { field: "company", headerName: "Firma" },
    { field: "city", headerName: "Miasto" },

    {
      field: "lastOverviewDate",
      headerName: "Data ostatniego przegladu",
    },
    {
      field: "actions",
      sortable: false,
      headerName: "",
      flex: 1,

      renderCell: ({ row }: Partial<GridRowParams>) => (
        <ButtonGroup variant="contained" aria-label="Basic button group">
          <Button onClick={() => handleEditButtonClicked(row)}>Edytuj</Button>
          <Button onClick={() => handleDeleteButtonClicked(row)}>Usun</Button>
          <Button onClick={() => handleDetailsButtonClicked(row)}>
            Szczegoly
          </Button>
        </ButtonGroup>
      ),
    },
  ];

  return (
    <TableContainer component={Paper}>
      <DataGrid
        rows={rows}
        columns={columns}
        initialState={{
          pagination: {
            paginationModel: { page: 0, pageSize: 5 },
          },
        }}
        pageSizeOptions={[5, 10]}
      />
      <Button variant="contained" onClick={handleAddButtonClicked}>
        Dodaj
      </Button>
      <Button variant="contained" onClick={handleSeeMapButtonClicked}>
        Zobacz na mapie
      </Button>
      <CustomerEditDialog
        onEditCustomer={onEditCustomer}
        currentRow={currentRow}
        onClose={() => setEditDialogOpened(false)}
        open={editDialogOpened}
      />
      <CustomerAddDialog
        open={addDialogOpened}
        onClose={() => setAddDialogOpened(false)}
        onAddCustomer={onAddCustomer}
      />
      <MapDialog
        data={data}
        open={mapDialogOpened}
        onClose={() => setMapDialogOpened(false)}
      />
      <CustomerDeleteDialog
        open={deleteDialogOpened}
        onClose={() => setDeleteDialogOpened(false)}
        deleteId={currentRow.id}
        firstName={currentRow.firstName}
        lastName={currentRow.lastName}
        company={currentRow.company}
        onDeleteCustomer={onDeleteCustomer}
      />
      <CustomerDetailsDialog
        currentRow={currentRow}
        onClose={() => setDetailsDialogOpened(false)}
        open={detailsDialogOpened}
      />
    </TableContainer>
  );
};
