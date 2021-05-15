import {Table, PageHeader, Image, Input, Space, Button} from "antd";
import React, {useEffect, useState} from "react";
import ContactsApi from "../api/ContactsApi";
import AppUtility from "../common/AppUtility";
import 'antd/dist/antd.css';
import { SearchOutlined } from '@ant-design/icons';
import Highlighter from 'react-highlight-words';

function Contacts() {
    const [contacts, setContacts] = useState(undefined);
    const [dataIsLoading, setDataIsLoading] = useState(false);
    const [contactsCount, setContactsCount] = useState(undefined);
    const [searchText, setSearchText] = useState(undefined);
    const [page, setPage] = useState(0);
    const [size, setSize] = useState(10);

    useEffect(() => {
        setDataIsLoading(true);

        if(!searchText) {
            ContactsApi.loadContacts(page, size, (response) => {
                setDataIsLoading(false);
                if(response.data) {
                    setContacts(response.data.content);
                    setContactsCount(response.data.totalElements)
                }
            }, (error) => {
                setDataIsLoading(false);
                setContacts(undefined);
                setContactsCount(0);
                AppUtility.showErrorText("Error","Could not load contacts!" )
            })
        } else {
            ContactsApi.searchContacts(searchText, page, size, (response) => {
                setDataIsLoading(false);
                if(response.data) {
                    setContacts(response.data.content);
                    setContactsCount(response.data.totalElements)
                }
            }, (error) => {
                setDataIsLoading(false);
                setContacts(undefined);
                setContactsCount(0);
                AppUtility.showErrorText("Error","Could not load contacts!" )
            })
        }
    }, [page, size, searchText]);

    const getColumnSearchProps = dataIndex => ({
        filterDropdown: ({ setSelectedKeys, selectedKeys, confirm, clearFilters }) => (
            <div style={{ padding: 8 }}>
                <Input
                    placeholder={`Search ${dataIndex}`}
                    value={selectedKeys[0]}
                    onChange={e => setSelectedKeys(e.target.value ? [e.target.value] : [])}
                    onPressEnter={() => handleSearch(selectedKeys, confirm)}
                    style={{ marginBottom: 8, display: 'block' }}
                />
                <Space>
                    <Button
                        type="primary"
                        onClick={() => handleSearch(selectedKeys, confirm)}
                        icon={<SearchOutlined />}
                        size="small"
                        style={{ width: 90 }}
                    >
                        Search
                    </Button>
                    <Button onClick={() => handleReset(clearFilters)} size="small" style={{ width: 90 }}>
                        Reset
                    </Button>
                    <Button
                        type="link"
                        size="small"
                        onClick={() => {
                            confirm({ closeDropdown: false });
                            setSearchText(selectedKeys[0]);
                        }}
                    >
                        Filter
                    </Button>
                </Space>
            </div>
        ),
        filterIcon: filtered => <SearchOutlined style={{ color: filtered ? '#1890ff' : undefined }} />,
        onFilter: (value, record) =>
            record[dataIndex]
                ? record[dataIndex].toString().toLowerCase().includes(value.toLowerCase())
                : '',
        onFilterDropdownVisibleChange: visible => {
            if (visible) {
                //setTimeout(() => searchInput.select(), 100);
            }
        },
        render: text =>
                <Highlighter
                    highlightStyle={{ backgroundColor: '#ffc069', padding: 0 }}
                    searchWords={[searchText]}
                    autoEscape
                    textToHighlight={text ? text.toString() : ''}
                />
    });

    const handleSearch = (selectedKeys, confirm) => {
        confirm();
        setSearchText(selectedKeys[0])
    };

    const handleReset = clearFilters => {
        clearFilters();
        setSearchText('');
    };

    const columns = [
        {
            title: 'ID',
            dataIndex: 'id',
            key: 'id',
        },
        {
            title: 'Avatar',
            dataIndex: 'avatarUrl',
            key: 'avatarUrl',
            render: avatarUrl => <Image width={30} height={40} src={avatarUrl}/>
        },
        {
            title: 'Name',
            dataIndex: 'name',
            key: 'name',
            ...getColumnSearchProps('name')
        }
    ]

    const paginationData = {
        total: contactsCount,
        showSizeChanger: true,
        pageSizeOptions: ['10', '25', '50', '100', '150', '250', '500', '1000'],
        defaultCurrent: 1,
    };

    const tableOnChange = (change) => {
        setPage(change.current - 1);
        setSize(change.pageSize);
    };



    return (
        <div style={{margin: 50}}>
            <PageHeader
                className="site-page-header"
                title="My Contacts"
                subTitle="This page shows the list of your contacts."
            />
            <Table
                loading={dataIsLoading}
                rowKey={record => record.id}
                size='small'
                columns={columns}
                dataSource={contacts ? contacts : []}
                pagination={paginationData}
                onChange={ change => tableOnChange(change)}
            />
        </div>

    )
};

export default Contacts;